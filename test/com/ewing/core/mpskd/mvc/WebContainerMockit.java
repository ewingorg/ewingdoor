package com.ewing.core.mpskd.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;

import mockit.Expectations;
import mockit.Mocked;

/**
 * WEB容器模拟
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class WebContainerMockit {

    @Mocked
    protected HttpServletRequest req;

    @Mocked
    protected HttpServletResponse resp;

    @Before
    public void init() {
        new Expectations() {
            {
                // 模拟服务器检验参数
                req.getParameterMap();
                Map<String, String[]> data = new HashMap<String, String[]>();
                data.put("signature", new String[]{"8f89b331558f93dd1931f0c01a1dc121e1ef744a"});
                data.put("timestamp", new String[]{"1435267"});
                data.put("nonce", new String[]{"ZnFI4vNoiAqbmPyQ-bkOktOt9x"});
                data.put("echostr", new String[]{"123456"});
                returns(data);

            }
        };
    }
}
