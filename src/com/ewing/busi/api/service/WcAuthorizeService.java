package com.ewing.busi.api.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.api.dto.WxWebAuthorizeReq;
import com.ewing.busi.customer.constants.AccountThirdPlatform;
import com.ewing.busi.customer.model.Customer;
import com.ewing.busi.customer.model.CustomerThirdAccount;
import com.ewing.busi.customer.service.CustomerThirdAccountService;
import com.ewing.common.constants.IsEff;
import com.ewing.core.auth.HttpSessionUtils;
import com.ewing.core.auth.SessionUserDetails;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.mpsdk.WxPropertyManager;
import com.ewing.core.mpsdk.api.UserAPI;
import com.ewing.core.mpsdk.api.WechatAPIImpl;
import com.ewing.core.mpsdk.vo.api.WebAuthorizationDto;
import com.ewing.core.mpsdk.vo.api.WebAuthorizationUserInfo;
import com.ewing.core.redis.RedisException;
import com.sun.star.uno.RuntimeException;

@Repository("wcAuthorizeService")
public class WcAuthorizeService {
  
  private UserAPI userApi = new WechatAPIImpl();
  @Resource
  private BaseDao baseDao;
  @Resource
  private CustomerThirdAccountService customerThirdAccountService;
  
  /**
   * 获取用户微信网页获取用户信息的第一步骤的code
   * 
   * @author Joeson
   */
  public void getWebAccessTocken(WxWebAuthorizeReq req){
    if(null == req){
      return;
    }
    
    WxPropertyManager manager = WxPropertyManager.getInstance();
    WebAuthorizationDto getWebAccessTokenResp = userApi.getWebAccessToken(manager.getAppId(), manager.getSecret(), req.getCode());
    if(null == getWebAccessTokenResp){
      throw new RuntimeException("网页授权获取用户基本信息-第二步：通过code换取网页授权access_token,响应为空");
    }
    //网页授权获取用户基本信息 刷新token可以不用
    //WebAuthorizationDto refreshTokenResp = userApi.refreshWebAccessToken(manager.getAppId(), getWebAccessTokenResp.getRefreshToken());
   
    WebAuthorizationUserInfo userInfo = userApi.getWebUserInfo(getWebAccessTokenResp.getAccessToken(), getWebAccessTokenResp.getOpenId(), UserAPI.LANG_CN);
    if(null == userInfo){
      throw new RuntimeException("网页授权获取用户基本信息-第三步：获取用户信息失败");
    }
    CustomerThirdAccount thirdAccount = customerThirdAccountService.findByUserIdAndPlatId(userInfo.getOpenId(), AccountThirdPlatform.WE_CHAT.getValue());
    if(null == thirdAccount){
      Customer customer = new Customer();
      customer.setHeadIcon(userInfo.getHeadImgUrl());
      customer.setName(userInfo.getNickName());
      customer.setIseff(IsEff.EFFECTIVE.getValue());
      customer.setCreateTime(new Date());
      baseDao.save(customer);
      
      thirdAccount = new CustomerThirdAccount();
      thirdAccount.setCustomerId(customer.getId());
      thirdAccount.setHeadIcon(userInfo.getHeadImgUrl());
      thirdAccount.setNickName(userInfo.getNickName());
      thirdAccount.setUserId(userInfo.getOpenId());
      thirdAccount.setCreateTime(new Date());
      baseDao.save(thirdAccount);
    }

    //设置登陆session
    try {
      HttpSessionUtils.setWXSessionUserDetails(userInfo, thirdAccount.getCustomerId());
    } catch (RedisException e) {
      e.printStackTrace();
    }
  }
    

}
