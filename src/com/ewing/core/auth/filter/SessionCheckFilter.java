package com.ewing.core.auth.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ewing.core.auth.HttpSessionUtils;


/**
 * Ajax请求session检查过滤器，可以配置忽略检查的uri：完全匹配的地址/web/abc/abc，通配符形式/web/fs/**
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年3月7日
 */
public class SessionCheckFilter implements Filter {

  /**
   * 忽略检查
   */
  private static final String ignoreUriList = "ignoreUriList";
  
  /**
   * 全部忽略
   */
  private static final String ignoreAll = "/**";
  
  
  private Set<String> ignoreUris = null;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    ignoreUris = new HashSet<String>();
    String sIgnoreUriList = filterConfig.getInitParameter(ignoreUriList);
    if (sIgnoreUriList == null || sIgnoreUriList.length() == 0) {
      return;
    }
    String[] uris = sIgnoreUriList.split(",");
    for (String uri : uris) {
      ignoreUris.add(uri);
    }
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    String uri = req.getRequestURI();
    String context = req.getContextPath();
    String requestPath = uri.substring(context.length());
    if (ignoreCheck(requestPath)) {
      chain.doFilter(request, response);
      return;
    }
    // 如果没有PreSessionUserDetails，说明没有登录过，或者登录过但是用户清空了浏览器缓存，需要第三方登陆验证
    if (req.getSession(false) == null || null == HttpSessionUtils.getPreSessionUserDetails()) {
      HttpServletResponse resp = (HttpServletResponse) response;
      
      //@DODO重定向到第三方登陆，登陆之后设置SessionUserDetails
    }
    
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
  }
  
  /**
   * 当前请求是否忽略检查，默认返回false，表示需要检查，实现参考AntPathRequestMatcher
   * @param request
   * @return
   * created by xuzhw on 2015年10月9日 下午2:09:32
   */
  private boolean ignoreCheck(String requestPath) {
    // 如果忽略的uri中包含/**则表示不需要检查
    if (ignoreUris.contains(ignoreAll)) {
      return true;
    }
    boolean result = false;
    for (String ignoreUri : ignoreUris) {
      // 如果忽略的uri形如/web/fs/**
      if (ignoreUri.endsWith(ignoreAll) && ignoreUri.indexOf("*") == ignoreUri.length() - 2) {
        // 得到/web/fs
        String subpath = ignoreUri.substring(0, ignoreUri.length() - 3);
        int length = subpath.length();
        // 忽略/web/fs、/web/fs/、/web/fs/abc
        result = requestPath.startsWith(subpath) 
            && (requestPath.length() == length || requestPath.charAt(length) == '/'); 
      }
      else {
        result = requestPath.equals(ignoreUri);
      }
      // 一旦确定为忽略检查,就跳出循环
      if (result) {
        break;
      }
    }
    return result;
  }
  
}
