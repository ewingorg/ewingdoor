package com.ewing.core.auth;

/**
 * Session级别的回话，记录用户登录状态，如果从ServletRequestAttribute中获取不到PreSessionUserDetails，标识着用户没有登录状态，需要进行第三方平台（
 * 微信、QQ）的登陆验证；
 * 如果获取到PreSessionUserDetails，用PreSessionUserDetails.userSessionId则可以到缓存中获取客户的SessionUserDetails
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年3月7日
 */
public abstract class SessionUserDetails {
  
  /**
   * userSessionId是到缓存获取，UserDetails的一个key<br/>
   * 考虑存储过多的UserDetails到内存，会是tomcat爆内存，
   */
  private String userSessionId;
  /**
   * 平台客户id customer id
   */
  private Integer cusId;
  
  public String getUserSessionId() {
    return userSessionId;
  }

  public void setUserSessionId(String userSessionId) {
    this.userSessionId = userSessionId;
  }

  public Integer getCusId() {
    return cusId;
  }

  public void setCusId(Integer cusId) {
    this.cusId = cusId;
  }
}
