package com.ewing.core.mpskd.api;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.ewing.core.mpsdk.api.MessageAPI;
import com.ewing.core.mpsdk.api.WechatAPIImpl;
import com.ewing.core.mpsdk.vo.api.Template;

/**
 * MessageAPI 测试
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class MessageAPITest extends APITestSupport {

    private static final Log log = Logs.get();

    private MessageAPI ma;

    private String openId;

    @Before
    public void init() {
        log.info("====== MessageAPITest ======");
        super.init();
        this.openId = _cr.get("openId");
        ma = WechatAPIImpl.create(mpAct);
    }

    /**
     * 发送模板消息
     * 
     * @author Joeson
     */
    @Test
    public void testSendTemplateMsg() {
        log.info("====== MessageAPI#sendTemplateMsg ======");
        String tmlId = _cr.get("tmplId");
        Template tml = new Template("title", "测试");
        String url = "https://www.google.com";
        long msgid = ma.sendTemplateMsg(openId, tmlId, "#119EF3", url, tml);
        assertTrue(msgid > 0);
        log.info(msgid);
    }

}
