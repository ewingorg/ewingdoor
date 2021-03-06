package com.ewing.core.mpskd.mvc;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;

import mockit.Expectations;
import mockit.integration.junit4.JMockit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.ewing.core.mpsdk.utils.StreamTool;

/**
 * WechatWebSupport 测试
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
@RunWith(JMockit.class)
public class WechatWebSupportTest extends WebContainerMockit {

    private static final Log log = Logs.get();

    @Override
    @Before
    public void init() {
        log.info("====== WechatWebSupportTest ======");
        super.init();
    }

    @Test
    public void testCheck() throws IOException {

        log.info("====== WechatWebSupportTest-get ======");
        new Expectations() {
            {
                req.getMethod();
                returns("GET");
            }
        };

        WebSupportContainer wsct = new WebSupportContainer();
        wsct.wechat(req, resp);
    }

    @Test
    public void testInteract() throws IOException {

        log.info("====== WechatWebSupportTest-post ======");
        new Expectations() {
            {
                // 模拟微信互动数据
                req.getInputStream();
                ServletInputStream sis = new ServletInputStream() {

                    String textxml = "<xml>"
                                     + "<ToUserName><![CDATA[gh_15d5865s2c]]></ToUserName>\n"
                                     + "<FromUserName><![CDATA[oa_H3239023j32324243]]></FromUserName>\n"
                                     + "<CreateTime>1418182341</CreateTime>\n"
                                     + "<MsgType><![CDATA[text]]></MsgType>\n"
                                     + "<Content><![CDATA[你好!]]></Content>\n"
                                     + "<MsgId>6091046778677430</MsgId>\n"
                                     + "</xml>";

                    InputStream is = StreamTool.toStream(textxml);

                    @Override
                    public int read() throws IOException {
                        return is.read();
                    }
                };
                returns(sis);

                req.getMethod();
                returns("POST");
            }
        };

        WebSupportContainer wsct = new WebSupportContainer();
        wsct.wechat(req, resp);
    }

}
