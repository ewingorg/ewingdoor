package com.ewing.core.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ewing.core.mpsdk.vo.api.WebAuthorizationUserInfo;
import com.ewing.core.redis.RedisException;
import com.ewing.core.redis.RedisManage;
import com.ewing.utils.BeanCopy;
import com.ewing.utils.BizGenerator;


/**
 * session 工具类
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年3月7日
 */
public final class HttpSessionUtils {
  /**
   * 默认生成的该类的LOG记录器，使用slf4j组件。避免产生编译警告，使用protected修饰符。
   */
  private static Logger logger = Logger.getLogger(HttpSessionUtils.class);

  /**
   * 从servletrequestattribute获取的PreSessionUserDetails的key
   */
  public static final String USER_SESSION_ID_KEY = "usersessionid";

  /**
   * 从缓存中获取key的
   */
  public static final String USER_DETAILS_CACHE_KEY = "userdetailscache_";

  public static final String REDICT_URL = "redirect_url";

  /**
   * 标识用户是否已经登陆
   * 
   * @author Joeson
   */
  public static boolean isLogined() {
    return null != getPreSessionUserDetails();
  }

  /**
   * 获取客户PreSessionUserDetails信息
   * 
   * @author Joeson
   */
  public static PreSessionUserDetails getPreSessionUserDetails() {
    HttpSession session = getSession();
    return session == null ? null : (PreSessionUserDetails) session
        .getAttribute(USER_SESSION_ID_KEY);
  }

  /**
   * 获取customerId，customer id
   * 
   * @author Joeson
   */
  public static Integer getCusId() {
    PreSessionUserDetails preSessionUserDetails = getPreSessionUserDetails();
    if (null == preSessionUserDetails) {
      return 0;
    } else {
      return preSessionUserDetails.getCusId();
    }
  }

  /**
   * 需要根据PreSessionUserDetails到缓存中获取
   * 
   * @author Joeson
   * 
   * @throws RedisException
   */
  public static SessionUserDetails getSessionUserDetails() throws RedisException {
    PreSessionUserDetails preSessionUserDetails = getPreSessionUserDetails();
    if (null == preSessionUserDetails) {
      return null;
    } else {
      // 从缓存中获取UserDetails
      try {
        return RedisManage.getInstance().get(
            USER_DETAILS_CACHE_KEY + preSessionUserDetails.getUserSessionId());
      } catch (RedisException e) {
        logger.error(e.getMessage(), e);
        throw e;
      }
    }
  }

  /**
   * 设置session中UserDetails
   * 
   * @param userInfo
   * @author Joeson
   * @throws RedisException
   */
  public static void setWXSessionUserDetails(WebAuthorizationUserInfo userInfo, Integer cusId)
      throws RedisException {
    if (null == userInfo) {
      throw new IllegalStateException("userInfo 为空");
    }

    HttpSession session = getSession();
    // PreSessionUserDetails保存到Session中
    PreSessionUserDetails preSessionUserDetails = new PreSessionUserDetails();
    preSessionUserDetails.setCusId(cusId);
    preSessionUserDetails.setOpenId(userInfo.getOpenId());
    preSessionUserDetails.setUserSessionId(generateSessionId());
    session.setAttribute(USER_SESSION_ID_KEY, preSessionUserDetails);

    // 详细的SessionUserDetails信息，保存到redis缓存中
    WXSessionUserDetails userDetails = new WXSessionUserDetails();
    BeanCopy.copy(userDetails, userInfo, true);

    try {
      RedisManage.getInstance().set(
          USER_DETAILS_CACHE_KEY + preSessionUserDetails.getUserSessionId(), userDetails);
    } catch (RedisException e) {
      logger.error(e.getMessage(), e);
      throw e;
    }
  }

  /**
   * 设置重定向地址
   * @param url 重定向地址
   * @author Joeson
   */
  public static void setRedirectUrl(String url) {
    HttpSession session = getSession();

    if (null == session) {
      throw new IllegalStateException("current session is invalid");
    }
    // 设置重定向地址
    session.setAttribute(REDICT_URL, url);
  }

  /**
   * 获取重定向地址
   * @author Joeson
   * @param remove 是否进行删除
   */
  public static String getRedirectUrl(boolean remove) {
    HttpSession session = getSession();

    if (null == session) {
      throw new IllegalStateException("current session is invalid");
    }
    // 获取重定向地址
    String redirectUrl = (String) session.getAttribute(REDICT_URL);
    if(remove){
      session.removeAttribute(REDICT_URL);
    }
    
    return redirectUrl;
  }
  
  public static HttpServletRequest getRequest() {
    HttpServletRequest request = (HttpServletRequest) RequestHolder.getRequest();
    if (request == null) {
      throw new IllegalStateException("current request dosnot exists requestholder");
    }

    return request;
  }
  
  /**
   * 当前用户是否正在微信验证中<br/>
   * 如果重定向地址不为空，说明当前用户正在微信验证中（重定向地址是微信验证成功后作为重定向地址，验证成功之后会做清除处理）
   * @author Joeson
   */
  public static boolean isAuthorzing(){
    return StringUtils.isNotEmpty(getRedirectUrl(false));
  }

  public static HttpSession getSession() {
    HttpServletRequest request = (HttpServletRequest) RequestHolder.getRequest();
    if (request == null) {
      throw new IllegalStateException("current request dosnot exists requestholder");
    }

    return request.getSession();
  }

  /**
   * 生成session key
   * 
   * @author Joeson
   */
  private static String generateSessionId() {
    return "sessionkey_" + BizGenerator.generateBizNum();
  }
}
