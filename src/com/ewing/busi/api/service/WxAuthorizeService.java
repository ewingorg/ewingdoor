package com.ewing.busi.api.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.digester.Digester;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.misc.Sort;

import com.ewing.busi.api.action.WxAuthorizeAction;
import com.ewing.busi.api.dto.WxAuthServerReq;
import com.ewing.busi.api.dto.WxWebAuthorizeReq;
import com.ewing.busi.customer.constants.AccountThirdPlatform;
import com.ewing.busi.customer.model.Customer;
import com.ewing.busi.customer.model.CustomerThirdAccount;
import com.ewing.busi.customer.service.CustomerThirdAccountService;
import com.ewing.common.constants.IsEff;
import com.ewing.core.auth.HttpSessionUtils;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.mpsdk.WxPropertyManager;
import com.ewing.core.mpsdk.api.UserAPI;
import com.ewing.core.mpsdk.api.WechatAPIImpl;
import com.ewing.core.mpsdk.vo.api.WebAuthorizationDto;
import com.ewing.core.mpsdk.vo.api.WebAuthorizationUserInfo;
import com.ewing.core.redis.RedisException;
import com.google.common.collect.Lists;
import com.sun.star.uno.RuntimeException;

@Repository("wcAuthorizeService")
public class WxAuthorizeService {
  
  private static Logger logger = Logger.getLogger(WxAuthorizeService.class);

  private UserAPI userApi = new WechatAPIImpl();
  @Resource
  private BaseDao baseDao;
  @Resource
  private CustomerThirdAccountService customerThirdAccountService;

  /**
   * 获取用户微信网页获取用户信息的第一步骤的code
   * 
   * @author Joeson
   * @throws RedisException 
   */
  public boolean getWebAuthCode(WxWebAuthorizeReq req) throws RedisException {
    if (null == req) {
      return false;
    }

    WxPropertyManager manager = WxPropertyManager.getInstance();
    WebAuthorizationDto getWebAccessTokenResp =
        userApi.getWebAccessToken(manager.getAppId(), manager.getSecret(), req.getCode());
    if (null == getWebAccessTokenResp) {
      throw new RuntimeException("网页授权获取用户基本信息-第二步：通过code换取网页授权access_token,响应为空");
    }
    // 网页授权获取用户基本信息 刷新token可以不用
    // WebAuthorizationDto refreshTokenResp = userApi.refreshWebAccessToken(manager.getAppId(),
    // getWebAccessTokenResp.getRefreshToken());

    WebAuthorizationUserInfo userInfo =
        userApi.getWebUserInfo(getWebAccessTokenResp.getAccessToken(),
            getWebAccessTokenResp.getOpenId(), UserAPI.LANG_CN);
    if (null == userInfo) {
      throw new RuntimeException("网页授权获取用户基本信息-第四步：获取用户信息失败");
    }
    CustomerThirdAccount thirdAccount =
        customerThirdAccountService.findByUserIdAndPlatId(userInfo.getOpenId(),
            AccountThirdPlatform.WE_CHAT.getValue());
    if (null == thirdAccount) {
      Customer customer = new Customer();
      customer.setHeadIcon(userInfo.getHeadImgUrl());
      customer.setName(userInfo.getNickName());
      customer.setIseff(IsEff.EFFECTIVE.getValue());
      customer.setCreateTime(new Date());
      customer.setSex(userInfo.getSex());
      baseDao.save(customer);

      thirdAccount = new CustomerThirdAccount();
      thirdAccount.setCustomerId(customer.getId());
      thirdAccount.setThirdPlatform(AccountThirdPlatform.WE_CHAT.getValue());
      thirdAccount.setHeadIcon(userInfo.getHeadImgUrl());
      thirdAccount.setNickName(userInfo.getNickName());
      thirdAccount.setUserId(userInfo.getOpenId());
      thirdAccount.setCreateTime(new Date());
      baseDao.save(thirdAccount);
    }

    // 设置登陆session
    try {
      HttpSessionUtils.setWXSessionUserDetails(userInfo, thirdAccount.getCustomerId());
    } catch (RedisException e) {
      throw e;
    }
    
    return true;
  }

  /**
   * <pre>
   * 加密/校验流程如下：<br/>
   * 1. 将token、timestamp、nonce三个参数进行字典序排序
   * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
   * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
   * </pre>
   * 
   * @param req
   * @return
   * @author Joeson
   */
  public boolean authServer(WxAuthServerReq req) {
    Validate.notNull(req, "入参不能为空");
    String signature = req.getSignature();
    String timestamp = req.getTimestamp();
    String nonce = req.getNonce();
    String echostr = req.getEchostr();
    Validate.notNull(signature, "signature不能为空");
    Validate.notNull(timestamp, "timestamp不能为空");
    Validate.notNull(nonce, "nonce不能为空");
    Validate.notNull(echostr, "echostr不能为空");

    List<String> tmp = Lists.newArrayList();
    WxPropertyManager manager = WxPropertyManager.getInstance();
    tmp.add(manager.getToken());
    tmp.add(timestamp);
    tmp.add(nonce);
    Collections.sort(tmp, new Comparator<String>() {

      @Override
      public int compare(String o1, String o2) {
        return o1.compareTo(o2);
      }
    });
    String validSign = DigestUtils.shaHex(tmp.get(0) + tmp.get(1) + tmp.get(2));
    
    logger.info("validSign : " + validSign);
    return StringUtils.equals(validSign, signature);
  }
}
