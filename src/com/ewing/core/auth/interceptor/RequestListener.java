package com.ewing.core.auth.interceptor;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import com.ewing.core.auth.RequestHolder;

/**
 * 实现request初始化、销毁，RequestHolder中request的操作
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年3月8日
 *
 */
public class RequestListener implements ServletRequestListener {

  /**
   * 当请求进来时候将request设置到RequestHolder中，共后续使用
   */
  @Override
  public void requestInitialized(ServletRequestEvent requestEvent) {
    if (!(requestEvent.getServletRequest() instanceof HttpServletRequest)) {
      throw new IllegalArgumentException("Request is not an HttpServletRequest: "
          + requestEvent.getServletRequest());
    }
    HttpServletRequest request = (HttpServletRequest) requestEvent.getServletRequest();
    RequestHolder.setRequest(request);
  }

  @Override
  public void requestDestroyed(ServletRequestEvent requestEvent) {
    if (!(requestEvent.getServletRequest() instanceof HttpServletRequest)) {
      throw new IllegalArgumentException("Request is not an HttpServletRequest: "
          + requestEvent.getServletRequest());
    }

    HttpServletRequest request = (HttpServletRequest) requestEvent.getServletRequest();
    HttpServletRequest threadRequest = (HttpServletRequest) RequestHolder.getRequest();
    if (null != threadRequest) {
      // We're assumably within the original request thread...
      if (request == null) {
        request = threadRequest;
      }

      RequestHolder.removeRequest();
    }
  }
}
