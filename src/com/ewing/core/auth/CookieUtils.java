package com.ewing.core.auth;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.ewing.common.utils.SystemPropertyUtils;

/**
 * cookie的统一工具类
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年3月31日
 */
public class CookieUtils {

  private static final int COOKIE_MAX_AGE = Integer.MAX_VALUE;
  
  public static void removeCookie(HttpServletRequest request,
      HttpServletResponse response, String name) {
    if (null == name) {
      return;
    }
    Cookie cookie = getCookie(request, name);
    if(null != cookie){
      cookie.setPath(SystemPropertyUtils.COOKIE_DOMAIN);
      cookie.setValue("");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }
  }
  
  public static String getCookieValue(HttpServletRequest request, String name) {
    Cookie cookie = getCookie(request, name);
    return null != cookie ? cookie.getValue() : StringUtils.EMPTY;
  }

  /**
   * 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
   * 
   * @param request
   * @param name
   * @return
   */
  public static Cookie getCookie(HttpServletRequest request, String name) {
    Cookie[] cookies = request.getCookies();
    if (null == cookies || null == name || name.length() == 0) {
      return null;
    }
    Cookie cookie = null;
    for (Cookie c : cookies) {
      if (name.equals(c.getName())) {
        cookie = c;
        break;
      }
    }
    return cookie;
  }

  /**
   * 添加一条新的Cookie，默认7天过期时间(单位：秒)
   * 
   * @param response
   * @param name
   * @param value
   */
  public static void setCookie(HttpServletResponse response, String name,
      String value) {
    setCookie(response, name, value, COOKIE_MAX_AGE);
  }

  /**
   * 添加一条新的Cookie，可以指定过期时间(单位：秒)
   * 
   * @param response
   * @param name
   * @param value
   * @param maxValue
   */
  public static void setCookie(HttpServletResponse response, String name,
      String value, int maxValue) {
    if (null == name) {
      return;
    }
    if (null == value) {
      value = "";
    }
    Cookie cookie = new Cookie(name, value);
    cookie.setPath(SystemPropertyUtils.COOKIE_DOMAIN);
    if (maxValue != 0) {
      cookie.setMaxAge(maxValue);
    } else {
      cookie.setMaxAge(COOKIE_MAX_AGE);
    }
    response.addCookie(cookie);
  }
}
