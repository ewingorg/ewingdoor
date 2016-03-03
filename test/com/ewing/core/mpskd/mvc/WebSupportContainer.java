package com.ewing.core.mpskd.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    private static ConfigReaderUtils _cr;

    static {
        _cr = new ConfigReaderUtils("conf/biz/properties/mpconf.properties");
    }

    @Override
    public void init() {
        MPAccount mpAct = new MPAccount();
        mpAct.setMpId(_cr.get("mpId"));
        mpAct.setAppId(_cr.get("appId"));
        mpAct.setAppSecret(_cr.get("appSecret"));
        mpAct.setToken(_cr.get("token"));
        _wk.setMpAct(mpAct);
        _wk.setWechatHandler(new WechatDefHandler());
    }

    public void wechat(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.interact(req, resp);
    }
}
