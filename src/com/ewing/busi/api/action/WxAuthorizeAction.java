package com.ewing.busi.api.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.api.dto.WxWebAuthorizeReq;
import com.ewing.busi.api.service.WxAuthorizeService;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.utils.JsonUtils;

/**
 * 微信网页获取用户信息-第二步 回调接口
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月23日
 */
public class WxAuthorizeAction extends BaseAction {

  private static Logger logger = Logger.getLogger(WxAuthorizeAction.class);

  @Resource
  private static WxAuthorizeService wxAuthorizeService;

  /**
   * 获取用户微信网页获取用户信息的第一步骤的code
   * 
   * @author Joeson
   */
  public void getWebAccessTocken(){
    
    WxWebAuthorizeReq req = null;
    try {
      req = getParamJson(WxWebAuthorizeReq.class);
      
      wxAuthorizeService.getWebAccessTocken(req);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println(JsonUtils.toJson(req));

    
  }
}