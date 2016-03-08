package com.ewing.core.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * Request的一个全局操作类
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年3月8日
 */
public class RequestHolder {
  
private static final ThreadLocal<HttpServletRequest> reqTreadLocal = new ThreadLocal<HttpServletRequest>();
  
  public static HttpServletRequest getRequest(){
    return reqTreadLocal.get();
  }
  
  public static void setRequest(HttpServletRequest request){
    reqTreadLocal.set(request);
  }
  
  public static void removeRequest(){
    reqTreadLocal.remove();
  }
  
}
