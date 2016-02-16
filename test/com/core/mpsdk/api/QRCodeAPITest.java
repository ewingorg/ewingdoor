package com.core.mpsdk.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.ewing.core.mpsdk.api.QRCodeAPI;
import com.ewing.core.mpsdk.api.WechatAPIImpl;
import com.ewing.core.mpsdk.vo.api.QRTicket;

/**
 * QRCodeAPI 测试
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
// TODO 此API貌似有问题,以后再好好检测
public class QRCodeAPITest extends APITestSupport {

    private static final Log log = Logs.get();

    private QRCodeAPI qa;
    private int expireSeconds = 604800;
    private String ticket;

    @Override
    @Before
    public void init() {
        log.info("====== QRCodeAPITest ======");
        super.init();
        this.ticket = _cr.get("ticket");
        qa = WechatAPIImpl.create(mpAct);
    }

    /**
     * 创建二维码ticket 
     * 
     * @author Joeson
     */
    @Test
    public void testCreateQR() {
        log.info("====== QRCodeAPI#createQR ======");
        QRTicket qrt = qa.createQR(10, expireSeconds);
        assertNotNull(qrt);
        assertEquals(qrt.getExpireSeconds(), expireSeconds);
        log.infof("QR ticket: %s", qrt.getTicket());
        log.infof("QR expire time: %s", qrt.getExpireSeconds());
        log.infof("QR url: %s", qrt.getUrl());
    }

    /**
     * 通过ticket换取二维码
     * 
     * @author Joeson
     */
    @Test(expected = RuntimeException.class)
    public void testGetQR() {
        log.info("====== QRCodeAPI#getQR ======");
        File qrImg = qa.getQR(ticket);
        assertNotNull(qrImg);
        log.infof("temp path: %s", qrImg.getAbsolutePath());
    }

}
