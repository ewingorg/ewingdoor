package com.ewing.busi.api.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.api.dto.WxWebAuthorizeReq;
import com.ewing.busi.collect.dto.AddCollectReq;
import com.ewing.busi.collect.dto.DelCollectReq;
import com.ewing.busi.collect.dto.LightCollectReq;
import com.ewing.busi.collect.dto.LightCollectResp;
import com.ewing.busi.collect.service.CustomerCollectService;
import com.ewing.common.constants.AjaxRespCode;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.utils.JsonUtils;

/**
 * 
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月23日
 */
public class WcAuthorizeAction extends BaseAction {

  private static Logger logger = Logger.getLogger(WcAuthorizeAction.class);


  /**
   * 获取用户微信网页获取用户信息的第一步骤的code
   * 
   * @author Joeson
   */
  public void getWebAccessTocken(){
    
    WxWebAuthorizeReq req = null;
    try {
      req = getParamJson(WxWebAuthorizeReq.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println(JsonUtils.toJson(req));

    
  }
}