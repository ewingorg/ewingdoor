package com.ewing.core.mpskd.mvc;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.ResourceUtils;

import com.ewing.core.mpsdk.core.WechatDefHandler;
import com.ewing.core.mpsdk.mvc.WechatWebSupport;
import com.ewing.core.mpsdk.vo.MPAccount;
import com.ewing.utils.ConfigReaderUtils;

/**
 * WEB 容器
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class WebSupportContainer extends WechatWebSupport {

    private static ConfigReaderUtils reader;

    static {
      try {
        reader = new ConfigReaderUtils(ResourceUtils.getURL("classpath:config/properties/mpconf.properties").getFile());
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void init() {
        MPAccount mpAct = new MPAccount();
        mpAct.setMpId(reader.get("mpId"));
        mpAct.setAppId(reader.get("appId"));
        mpAct.setAppSecret(reader.get("appSecret"));
        mpAct.setToken(reader.get("token"));
        _wk.setMpAct(mpAct);
        _wk.setWechatHandler(new WechatDefHandler());
    }

    public void wechat(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.interact(req, resp);
    }
}
