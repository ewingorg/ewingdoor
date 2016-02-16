package com.core.mpsdk.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ewing.core.mpsdk.core.WechatDefHandler;
import com.ewing.core.mpsdk.mvc.HttpServletSupport;
import com.ewing.core.mpsdk.vo.MPAccount;
import com.ewing.utils.ConfigReaderUtils;

/**
 * Servlet容器
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class ServletContainer extends HttpServletSupport {

    private static final long serialVersionUID = 8954466678965988236L;

    private static ConfigReaderUtils _cr;

    static {
        _cr = new ConfigReaderUtils("conf/biz/properties/mpconf.properties");
    }

    @Override
    public void init() throws ServletException {
        MPAccount mpAct = new MPAccount();
        mpAct.setMpId(_cr.get("mpId"));
        mpAct.setAppId(_cr.get("appId"));
        mpAct.setAppSecret(_cr.get("appSecret"));
        mpAct.setToken(_cr.get("token"));
        _wk.setMpAct(mpAct);
        _wk.setWechatHandler(new WechatDefHandler());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        init();
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        init();
        super.doPost(req, resp);
    }
}
