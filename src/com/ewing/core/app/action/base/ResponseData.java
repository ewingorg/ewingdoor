package com.ewing.core.app.action.base;

/**
 * @author tanson lin
 * 
 * @create:2012-2-23
 * @description: action中向页面返回的封装信息.
 */
public class ResponseData {
  private Boolean success;
  private String retinfo;
  private String retCode;
  private Integer totalProperty;
  private String page;
  private Object result;
  private Integer respType;// 响应类型(由于使用前后端分离，有一些操作可能需要前端协助，比如redirect) 0 normal 1 forword 2 redirect

  
  /**
   * 当respType为redirect，result 为 重定向的url
   * @param success
   * @param result
   * @param respType
   */
  public ResponseData(Boolean success, Object result, Integer respType) {
    super();
    this.success = success;
    this.result = result;
    this.respType = respType;
  }

  public Integer getRespType() {
    return respType;
  }

  public void setRespType(Integer respType) {
    this.respType = respType;
  }

  public String getRetCode() {
    return retCode;
  }

  public void setRetCode(String retCode) {
    this.retCode = retCode;
  }

  public ResponseData() {

  }

  public String getRetinfo() {
    return retinfo;
  }

  public String getPage() {
    return page;
  }

  public void setPage(String page) {
    this.page = page;
  }

  public Boolean getSuccess() {
    return success;
  }

  public Integer getTotalProperty() {
    return totalProperty;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  public void setRetinfo(String retinfo) {
    this.retinfo = retinfo;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public void setTotalProperty(Integer totalProperty) {
    this.totalProperty = totalProperty;
  }
}
