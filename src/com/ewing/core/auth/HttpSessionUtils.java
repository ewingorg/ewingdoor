package com.ewing.core.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;

import com.ewing.busi.customer.model.CustomerThirdAccount;
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
  
  /**
   * 标识用户是否已经登陆
   * @return
   * @author Joeson
   */
  public static boolean isLogined(){
    return null != getPreSessionUserDetails();
  }
  
  /**
   * 获取客户PreSessionUserDetails信息
   * 
   * @return
   * @author Joeson
   */
  public static PreSessionUserDetails getPreSessionUserDetails() {
    HttpServletRequest request = (HttpServletRequest) RequestHolder.getRequest();
    if (request == null) {
      return null;
    }
    
    HttpSession session = request.getSession();
    return session == null ? null : (PreSessionUserDetails) session.getAttribute(USER_SESSION_ID_KEY);
  }
  
  /**
   * 获取customerId，customer id
   * @return
   * @author Joeson
   */
  public static Integer getCusId(){
    PreSessionUserDetails preSessionUserDetails = getPreSessionUserDetails();
    if(null == preSessionUserDetails){
      return 0;
    }else{
      return preSessionUserDetails.getCusId();
    }
  }
  
  /**
   * 需要根据PreSessionUserDetails到缓存中获取
   * @author Joeson
   * @throws RedisException 
   */
  public static SessionUserDetails getSessionUserDetails() throws RedisException{
    PreSessionUserDetails preSessionUserDetails = getPreSessionUserDetails();
    if(null == preSessionUserDetails){
      return null;
    }else{
      //从缓存中获取UserDetails
      try {
        return RedisManage.getInstance().get(USER_DETAILS_CACHE_KEY + preSessionUserDetails.getUserSessionId());
      } catch (RedisException e) {
        logger.error(e.getMessage(), e);
        throw e;
      }
    }
  }
  
  /**
   * 设置session中UserDetails
   * @param userInfo
   * @author Joeson
   * @throws RedisException 
   */
  public static void setWXSessionUserDetails(WebAuthorizationUserInfo userInfo, Integer cusId) throws RedisException{
    if(null == userInfo){
      throw new IllegalStateException("userInfo 为空");
    }
    
    HttpServletRequest request = (HttpServletRequest) RequestHolder.getRequest();
    if (request == null) {
      throw new IllegalStateException("current request dosnot exists requestholder");
    }
    
    HttpSession session = request.getSession();
    //PreSessionUserDetails保存到Session中
    PreSessionUserDetails preSessionUserDetails = new PreSessionUserDetails();
    preSessionUserDetails.setCusId(cusId);
    preSessionUserDetails.setOpenId(userInfo.getOpenId());
    preSessionUserDetails.setUserSessionId(generateSessionId());
    session.setAttribute(USER_SESSION_ID_KEY, preSessionUserDetails);
    
    //详细的SessionUserDetails信息，保存到redis缓存中
    WXSessionUserDetails userDetails = new WXSessionUserDetails();
    BeanCopy.copy(userDetails, userInfo, true);
   
    try {
      RedisManage.getInstance().set(USER_DETAILS_CACHE_KEY + preSessionUserDetails.getUserSessionId(), userDetails);
    } catch (RedisException e) {
      logger.error(e.getMessage(), e);
      throw e;
    }
  }
  
  /**
   * 生成session key
   * @author Joeson
   */
  private static String generateSessionId(){
    return "sessionkey_" + BizGenerator.generateBizNum();
  }
}
