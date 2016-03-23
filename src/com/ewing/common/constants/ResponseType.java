package com.ewing.common.constants;

/**
 * 响应类型
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年3月21日
 */
public class ResponseType {
  
  /**
   * 正常响应
   */
  public static final int NORMAL = 0;
  
  /**
   * forword 一般在前后端分离的架构中，这种是比较少的，都是redirect为主
   */
  public static final int FORWORD = 1;
  
  /**
   * redirect
   */
  public static final int REDIRECT = 2;

}
