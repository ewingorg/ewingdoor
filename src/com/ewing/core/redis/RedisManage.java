package com.ewing.core.redis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.axis.utils.StringUtils;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import com.ewing.util.PropertyUtil;

/**
 * redis工具类
 * 
 * @author tansonlam
 * @createDate 2016年2月1日
 * 
 */
public class RedisManage {
    /**
     * 连接池
     */
    private ShardedJedisPool pool;
    /**
     * 是否已经初始化，防止重复调用构造函数
     */
    private static AtomicBoolean isInit = new AtomicBoolean(false);
    /**
     * 单例实例
     */
    private static RedisManage redisManage = new RedisManage();

    private RedisManage() {
        if (pool == null && !isInit.getAndSet(true)) {

            List<JedisShardInfo> jdsInfoList = parseHost();
            JedisPoolConfig config = new JedisPoolConfig();
            // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxActive(500);
            // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(5);
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWait(1000 * 100);
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);

            pool = new ShardedJedisPool(config, jdsInfoList, Hashing.MURMUR_HASH,
                    Sharded.DEFAULT_KEY_TAG_PATTERN);
        }
    }

    /**
     * 构建服务器地址信息
     * 
     * @return
     */
    private List<JedisShardInfo> parseHost() {
        String hosts = PropertyUtil.getProperty("redis.host");
        if (StringUtils.isEmpty(hosts))
            throw new RuntimeException("plz configure redis.host in system.properties");
        String[] hostArray = hosts.split(",");
        List<JedisShardInfo> jdsInfoList = new ArrayList<JedisShardInfo>(2);
        for (String address : hostArray) {
            String host = address.split(":")[0].trim();
            Integer port = Integer.valueOf(address.split(":")[1].trim());
            JedisShardInfo infoA = new JedisShardInfo(host, port);
            jdsInfoList.add(infoA);
        }
        return jdsInfoList;
    }

    public static RedisManage getInstance() {
        return redisManage;
    }

    /**
     * 返还到连接池
     * 
     * @param pool
     * @param redis
     */
    private void returnResource(ShardedJedis redis) {
        if (redis != null) {
            pool.returnResource(redis);
        }
    }

    /**
     * 获取单个对象缓存
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public <T> T get(String key) throws RedisException {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            byte[] data = jedis.get(key.getBytes());
            if (data == null)
                return null;
            return (T) SerializingTranscoder.deserialize(data);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            throw new RedisException(e);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 设置单个对象缓存
     * 
     * @param key
     * @param value
     * @return
     * @throws RedisException
     */
    public <T> String set(String key, T value) throws RedisException {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            byte[] data = SerializingTranscoder.serialize(value);
            return jedis.set(key.getBytes(), data); 
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            throw new RedisException(e);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 从列表的右边插入對象列表
     * 
     * @param key
     * @param datas
     * @throws RedisException
     */
    public <T> void rpush(String key, List<T> datas) throws RedisException {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            byte[][] bb = new byte[datas.size()][];
            for (int i = 0; i < datas.size(); i++) {
                bb[i] = SerializingTranscoder.serialize(datas.get(i));
            }
            jedis.rpush(key.getBytes(), bb);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            throw new RedisException(e);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 按照指定位置获取列表
     * 
     * @param key
     * @param start
     * @param end
     * @return
     * @throws RedisException
     */
    public <T> List<T> lrange(String key, int start, int end) throws RedisException {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            List<byte[]> list = jedis.lrange(key.getBytes(), start, end);
            if (list == null)
                return Collections.EMPTY_LIST;
            List<T> rList = new ArrayList<T>();
            for (byte[] b : list) {
                rList.add((T) SerializingTranscoder.deserialize(b));
            }
            return rList;
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            throw new RedisException(e);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 获取列表长度
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public long llen(String key) throws RedisException {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.llen(key.getBytes());
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            throw new RedisException(e);
        } finally {
            returnResource(jedis);
        }
    }

    public void hset(String key, String field, String value) throws RedisException {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.hset(key, field, value);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            throw new RedisException(e);
        } finally {
            returnResource(jedis);
        }
    }

    public String hget(String key, String field) throws RedisException {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hget(key, field);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            throw new RedisException(e);
        } finally {
            returnResource(jedis);
        }
    }

    public Map<String, String> hgetAll(String key) throws RedisException {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            Map<String, String> allMap = new HashMap<String, String>();
            Map<byte[], byte[]> map = jedis.hgetAll(key.getBytes());
            if (map == null)
                return Collections.EMPTY_MAP;
            for (byte[] mapkey : map.keySet()) {
                allMap.put(new String(mapkey), new String(map.get(mapkey)));
            }
            return allMap;
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            throw new RedisException(e);
        } finally {
            returnResource(jedis);
        }
    }
}