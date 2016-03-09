package com.ewing.core.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.ewing.busi.resource.model.WebResource;

/**
 * 类/接口注释
 * 
 * @author tansonlam
 * @createDate 2016年2月2日
 * 
 */
public class RedisManageTest {
    @Test
    public void testSetSimple() {
        String key = "test";
        try {
            RedisManage.getInstance().set(key, 1);
            Integer data = RedisManage.getInstance().get(key);
            System.out.println(data);
        } catch (RedisException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testSetBean() {
        try {
            WebResource resource = new WebResource();
            resource.setId(8);
            resource.setCreateTime(new Date());
            String key = "resource_8";
            RedisManage.getInstance().set(key, resource);
            WebResource data = RedisManage.getInstance().get(key);
            System.out.println(data);
        } catch (RedisException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testSetList() {
        try {
            List<WebResource> list = new ArrayList<WebResource>();

            WebResource resource1 = new WebResource();
            resource1.setId(7);
            resource1.setCreateTime(new Date());
            WebResource resource2 = new WebResource();
            resource2.setId(8);
            resource2.setCreateTime(new Date());
            list.add(resource1);
            list.add(resource2);
            String key = "resource_list";
            RedisManage.getInstance().rpush(key, list);
            List<WebResource> rlist = RedisManage.getInstance().lrange(key, 0, 2);
            System.out.println(rlist.size());
            for (WebResource r : rlist) {
                System.out.println("id:" + r.getId());
            }
            System.out.println(rlist.size());
        } catch (RedisException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testMap() {
        String key = "resource_map";
        try {
            RedisManage.getInstance().hset(key, "list_1", "2");
            RedisManage.getInstance().hset(key, "list_2", "3");
            String o = RedisManage.getInstance().hget(key, "list_1");
            Map<String, String> map = RedisManage.getInstance().hgetAll(key);
            System.out.println(map);
        } catch (RedisException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    @Test
    public void testZadd() {
        List<WebResource> list = new ArrayList<WebResource>();

        WebResource resource1 = new WebResource();
        resource1.setId(7);
        resource1.setCreateTime(new Date());
        WebResource resource2 = new WebResource();
        resource2.setId(8);
        resource2.setCreateTime(new Date());
        list.add(resource1);
        list.add(resource2);
        String key = "resource_list2";
        try {
            RedisManage.getInstance().zadd(key, list);
            RedisManage.getInstance().zadd(key, list);
        List<WebResource> rlist = RedisManage.getInstance().zrangeWithScore(key, 0, 10);
        System.out.println(rlist.size());
        for (WebResource r : rlist) {
            System.out.println("id:" + r.getId());
        }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
