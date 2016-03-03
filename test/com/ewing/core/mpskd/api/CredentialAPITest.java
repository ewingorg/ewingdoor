package com.ewing.core.mpskd.api;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.ewing.core.mpsdk.api.CredentialAPI;
import com.ewing.core.mpsdk.api.WechatAPIImpl;

/**
 * CredentialApi 测试
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CredentialAPITest extends APITestSupport {

    private static final Log log = Logs.get();

    private CredentialAPI ca;

    @Override
    @Before
    public void init() {
        log.info("====== CredentialAPITest ======");
        super.init();
        ca = WechatAPIImpl.create(mpAct);
    }

    /**
     * 获取微信服务凭证
     * 
     * @author Joeson
     */
    @Test
    public void testGetAccessToken() {
        log.info("====== CredentialAPI#getAccessToken ======");
        assertNotNull(ca.getAccessToken());
        accessToken = ca.getAccessToken();                        
        log.info(ca.getAccessToken());
    }

    /**
     * 获取微信服务器IP地址
     * 
     * @author Joeson
     */
    @Test
    public void testGetServerIps() {
        log.info("====== CredentialAPI#getServerIps ======");
        List<String> ips = ca.getServerIps();
        assertNotNull(ips);
        int i = 0;
        for (String ip : ips) {
            i++;
            log.infof("Wechat server[%d] ip: %s", i, ip);
        }
    }

    /**
     * 长链接转短链接
     * 
     * @author Joeson
     */
    @Test
    public void testGetShorUrl() {
        log.info("====== CredentialAPI#getShortUrl ======");
        String longurl = "https://mp.weixin.qq.com/wiki/10/165c9b15eddcfbd8699ac12b0bd89ae6.html";
        String shorurl = ca.getShortUrl(longurl);
        assertNotNull(shorurl);
        log.info(shorurl);
    }

    @Test
    public void testGetJSTicket() {
        log.info("====== CredentialAPI#getJSTicket ======");
        String jsticket = ca.getJSTicket();
        assertNotNull(jsticket);
        log.info(jsticket);
    }
}
