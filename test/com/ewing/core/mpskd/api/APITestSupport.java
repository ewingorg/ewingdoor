package com.ewing.core.mpskd.api;

import org.junit.Before;

import com.ewing.core.mpsdk.vo.MPAccount;
import com.ewing.core.mpskd.TestSupport;


public class APITestSupport extends TestSupport {

    protected MPAccount mpAct;
    protected static String openId;
    protected static String openId2;
    protected static int groupId;
    protected static String accessToken;
    protected static String mediaId;
    protected static String ticket;
    protected static String tmplId;

    @Before
    public void init() {
        mpAct = new MPAccount();
        mpAct.setMpId(reader.get("mpId"));
        mpAct.setAppId(reader.get("appId"));
        mpAct.setAppSecret(reader.get("appSecret"));

        openId = reader.get("openId");
        openId2 = reader.get("openId2");
        groupId = reader.getInt("groupId");
        accessToken = reader.get("accessToken");
        mediaId = reader.get("mediaId");
        ticket = reader.get("ticket");
        tmplId = reader.get("tmplId");
    }

}
