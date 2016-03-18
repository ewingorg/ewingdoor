package com.ewing.core.mpskd.api;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.ewing.core.mpsdk.api.MediaAPI;
import com.ewing.core.mpsdk.api.WechatAPIImpl;
import com.ewing.core.mpsdk.common.MediaType;
import com.ewing.core.mpsdk.vo.api.Media;

/**
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
@FixMethodOrder(MethodSorters.JVM)
public class MediaAPITest extends APITestSupport {

    private static final Log log = Logs.get();

    private MediaAPI ma;

    @Override
    @Before
    public void init() {
        log.info("====== MediaAPITest ======");
        super.init();
        ma = WechatAPIImpl.create(mpAct);
    }

    /**
     * 上传多媒体文件 
     * 
     * @author Joeson
     */
    @Test
    public void testUploadImage() {
        log.info("====== MediaAPI#upMedia ======");
        File media = new File(this.getClass().getResource("/mpsdk4j-logo.png").getFile());
        Media m = ma.upMedia(MediaType.image.name(), media);
        assertNotNull(m);
        log.info(m);
    }

    /**
     * 下载多媒体文件
     * 
     * @author Joeson
     */
    @Test
    public void testGet() {
        log.info("====== MediaAPI#dlMedia ======");
        String mediaId = reader.get("mediaId");
        File media = ma.dlMedia(mediaId);
        assertNotNull(media);
        log.info(media.getAbsolutePath());
        log.info(media.getName());
    }

}
