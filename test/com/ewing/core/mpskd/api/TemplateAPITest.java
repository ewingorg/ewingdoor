package com.ewing.core.mpskd.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.ewing.core.mpsdk.api.TemplateAPI;
import com.ewing.core.mpsdk.api.WechatAPIImpl;

/**
 * TemplateAPI 测试
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
// TODO 此为新接口待测试
public class TemplateAPITest extends APITestSupport {

    private static final Log log = Logs.get();

    @SuppressWarnings("unused")
    private TemplateAPI ta;

    @Override
    @Before
    public void init() {
        log.info("====== TemplateAPITest ======");
        super.init();
        ta = WechatAPIImpl.create(mpAct);
    }

    /**
     * 设置所属行业
     * 
     * @author Joeson
     */
    @Test
    public void testSetIndustry() {
        log.info("====== TemplateAPITest#setIndustry ======");
        boolean flag = ta.setIndustry(1, 2);
        assertTrue(flag);
        log.info(flag);
    }

    /**
     * 获得模板ID
     * 
     * @author Joeson
     */
    @Test
    public void testGetTemplateId() {
        log.info("====== TemplateAPITest#getTemplateId ======");
        String tmplid = ta.getTemplateId("5A90LqXLMHUVd0d1PFv-TezTxYWf2PBDV1APvAMeb1E");
        assertNotNull(tmplid);
        log.info(tmplid);
    }

}
