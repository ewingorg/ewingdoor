package com.ewing.core.redis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * redis的分布式锁
 * 
 * @author tansonlam
 * @createDate 2016年3月8日
 * 
 */
public class RedisLocker {

    /**
     * 连接池
     */
    private final ShardedJedisPool pool;

    private final RedisManage redisManage;
    /**
     * 默认锁键的前缀
     */
    private final static String PREFIX_LOCKKEY = "DISLOCK_";
    /**
     * 默认锁的有效时间为3秒
     */
    private final static int DEFAULT_EXPIRE_TIME = 3;

    private static RedisLocker redisConcurrent = new RedisLocker();

    public static RedisLocker getInstance() {
        return redisConcurrent;
    }

    private RedisLocker() {
        redisManage = RedisManage.getInstance();
        pool = redisManage.pool;
    }

    /**
     * 锁在给定的等待时间内空闲，则获取锁成功 返回true， 否则返回false
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public boolean tryLock(String key) throws RedisException {
        String logKey = PREFIX_LOCKKEY + key;
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();

            Long i = jedis.setnx(logKey, logKey);
            if (i == 1) {
                jedis.expire(logKey, DEFAULT_EXPIRE_TIME);
                return Boolean.TRUE;
            }
            Thread.sleep(50);
            return Boolean.FALSE;
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            throw new RedisException(e);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 释放锁
     * 
     * @throws RedisException
     */
    public void unLock(String key) throws RedisException {
        redisManage.del(PREFIX_LOCKKEY + key);
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

}
