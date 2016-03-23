package com.ewing.common.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ewing.utils.StringUtil;

/**
 * Jsonp的一个辅助请求类
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年3月21日
 */
public class AjaxJsonpUtils {

  private static Logger logger = Logger.getLogger(AjaxJsonpUtils.class);
  
  /**
   * 是否是jsonpa请求
   * @param request
   * @return
   * @author Joeson
   */
  public static boolean isJsonpRequest(HttpServletRequest request) {
    if(null == request){
      logger.error("request can not be null");
      return false;
    }
    
    if (!StringUtil.isEmpty(request.getParameter("callbackparam")))
      return true;
    return false;
  }

  /**
   * jsonp 响应返回json数据
   * @param request 当前请求
   * @param response 当前响应
   * @param json 响应json数据
   * @throws IOException
   * @author Joeson
   */
  public static void outJson(HttpServletRequest request, HttpServletResponse response, String json)
      throws IOException {
    response.setContentType("text/json");
    String callbackparam = request.getParameter("callbackparam");
    String jsonpResut = "ajax.jsonpCallback(" + json + ",'" + callbackparam + "')";
    logger.info(jsonpResut);
    response.getWriter().write(jsonpResut);
  }

}
