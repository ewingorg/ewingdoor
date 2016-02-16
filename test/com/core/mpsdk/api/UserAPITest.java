package com.core.mpsdk.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.ewing.core.mpsdk.api.UserAPI;
import com.ewing.core.mpsdk.api.WechatAPIImpl;
import com.ewing.core.mpsdk.vo.api.FollowList;
import com.ewing.core.mpsdk.vo.api.Follower;
import com.ewing.core.mpsdk.vo.api.Follower2;

/**
 * UserAPI 测试
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
@FixMethodOrder(MethodSorters.JVM)
public class UserAPITest extends APITestSupport {

    private static final Log log = Logs.get();

    private UserAPI ua;

    @Before
    public void init() {
        log.info("====== UserAPITest ====== ");
        super.init();
        ua = WechatAPIImpl.create(mpAct);
    }

    /**
     * 获取关注用户列表
     * 
     * @author Joeson
     */
    @Test
    public void testGetFollowerList() {
        log.info("====== UserAPI#getFollowerList ====== ");
        FollowList fl = ua.getFollowerList(null);
        assertNotNull(fl);
        log.info(fl);
    }

    /**
     * 获取用户基本信息(包括UnionID机制)
     * 
     * @author Joeson
     */
    @Test
    public void testGetFollower() {
        log.info("====== UserAPI#getFollower ====== ");
        Follower f = ua.getFollower(openId, null);
        assertNotNull(f);
        log.info(f);
    }

    /**
     * 设置用户备注名
     * 
     * @author Joeson
     */
    @Test
    public void testUpdateRemark() {
        log.info("====== UserAPI#updateRemark ====== ");
        boolean flag = ua.updateRemark(openId, "Youself");
        assertTrue(flag);
        log.info(flag);
    }

    /**
     * 批量获取用户基本信息[最多拉取100条]
     * 
     * @author Joeson
     */
    @Test
    public void testGetFollowers() {
        log.info("====== UserAPI#getFollowers ====== ");
        List<Follower2> getfs = new ArrayList<Follower2>();
        getfs.add(new Follower2(openId));
        getfs.add(new Follower2(_cr.get("openId2")));

        List<Follower> fs = ua.getFollowers(getfs);
        assertNotNull(fs);
        assertEquals(2, fs.size());
        log.info(fs);
    }
}
