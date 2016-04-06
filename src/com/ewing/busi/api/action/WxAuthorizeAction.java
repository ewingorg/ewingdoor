package com.ewing.busi.api.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ewing.busi.api.dto.WxAuthServerReq;
import com.ewing.busi.api.dto.WxWebAuthorizeReq;
import com.ewing.busi.api.service.WxAuthorizeService;
import com.ewing.common.constants.ResponseType;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.action.base.ResponseData;
import com.ewing.core.auth.CookieUtils;
import com.ewing.core.auth.HttpSessionUtils;
import com.ewing.core.redis.RedisManage;
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
  private WxAuthorizeService wxAuthorizeService;

  /**
   * 微信验证本服务器的接口<br/>
   * http://mp.weixin.qq.com/wiki/8/f9a0b8382e0b77d87b3bcc1ce6fbc104.html#<br/>
   * 开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
   * 
   * @author Joeson
   */
  public void authServer() {
    WxAuthServerReq req = null;
    try {
      String signature = request.getParameter("signature");
      String timestamp = request.getParameter("timestamp");
      String nonce = request.getParameter("nonce");
      String echostr = request.getParameter("echostr");
      req = new WxAuthServerReq(signature, timestamp, nonce, echostr);
      logger.info(JsonUtils.toJson(req));

      if (wxAuthorizeService.authServer(req)) {
        response.getWriter().write(req.getEchostr());
      } else {
        return;
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return;
    }
  }

  /**
   * 获取用户微信网页获取用户信息的第一步骤的code，并进行第二、三、四步骤，设置用户登陆信息
   * 
   * @author Joeson
   */
  public void getWebAuthCode() {
    try {
      WxWebAuthorizeReq req = getParamJson(WxWebAuthorizeReq.class);
      checkRequired(req, "req不能为空");
      
      logger.info(JsonUtils.toJson(req));

      boolean result = wxAuthorizeService.getWebAuthCode(req);
      String redirectUrl = HttpSessionUtils.getRedirectUrl(true);
      RedisManage.getInstance().del(req.getState());
      
      logger.info("redirectUrl : " + redirectUrl);
      if (result) {
        // 进行重定向
        outResult(ResponseData.initResponseData(true, request.getCookies(), redirectUrl,
            ResponseType.REDIRECT));
      } else {
        outFailResult("登陆验证失败");
      }
    } catch (Exception e) {
      HttpSessionUtils.getRedirectUrl(true);//
      logger.error(e.getMessage(), e);
    }
  }
}
