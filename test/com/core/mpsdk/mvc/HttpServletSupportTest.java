package com.core.mpsdk.mvc;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;

import mockit.Expectations;
import mockit.integration.junit4.JMockit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.xml.sax.SAXException;

import com.ewing.core.mpsdk.utils.StreamTool;

/**
 * HttpServletSupport 测试
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
@RunWith(JMockit.class)
public class HttpServletSupportTest extends WebContainerMockit {

    private static final Log log = Logs.get();

    @Override
    @Before
    public void init() {
        log.info("====== HttpServletSupportTest ======");
        super.init();
    }

    @Test
    public void testGetCheck() throws IOException, SAXException, ServletException {
        log.info("====== HttpServletSupportTest-get ======");

        ServletContainer sc = new ServletContainer();
        sc.doGet(req, resp);
    }

    @Test
    public void testInteract() throws IOException, SAXException, ServletException {
        log.info("====== HttpServletSupportTest-post ======");
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
            }
        };

        ServletContainer sc = new ServletContainer();
        sc.doPost(req, resp);
    }

}
