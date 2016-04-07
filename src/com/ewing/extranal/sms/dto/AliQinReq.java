package com.ewing.extranal.sms.dto;

import com.google.gson.annotations.SerializedName;

public abstract class AliQinReq {

  /**
   * API接口名称。
   */
  private String method;
  /**
   * TOP分配给应用的AppKey
   */
  private String app_key;
  /**
   * 当此API的标签上注明：“需要授权”，则此参数必传；“不需要授权”，则此参数不需要传；“可选授权”，则此参数为可选。
   * <a href="http://open.taobao.com/doc2/detail?spm=a3142.7395905.4.14.Cnm3ar&docType=1&articleId=102635&treeId=1">TOP颁发给应用的授权信息</a>
   */
  private String session;
  /**
   * 时间戳，格式为yyyy-MM-dd HH:mm:ss，时区为GMT+8，例如：2015-01-01 12:00:00。淘宝API服务端允许客户端请求最大时间误差为10分钟。
   */
  private String timestamp; 
  /**
   * 响应格式。默认为xml格式，可选值：xml，json。
   */
  private String format;
  /**
   * API协议版本，可选值：2.0。
   */
  private String v;
  /**
   *  合作伙伴身份标识
   */
  private String partner_id;
  /**
   * 被调用的目标AppKey，仅当被调用的API为第三方ISV提供时有效
   */
  private String target_app_key;
  /**
   *  是否采用精简JSON返回格式，仅当format=json时有效，默认值为：false
   */
  private Boolean simplify;
  /**
   *  签名的摘要算法，可选值为：hmac，md5
   */
  private String sign_method; 
  /**
   * API输入参数签名结果<a href="http://open.taobao.com/doc/detail.htm?spm=a3142.7395905.4.15.Cnm3ar&id=101617">签名算法介绍</a>
   */
  private String sign;
  public String getMethod() {
    return method;
  }
  public void setMethod(String method) {
    this.method = method;
  }
  public String getApp_key() {
    return app_key;
  }
  public void setApp_key(String app_key) {
    this.app_key = app_key;
  }
  public String getSession() {
    return session;
  }
  public void setSession(String session) {
    this.session = session;
  }
  public String getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
  public String getFormat() {
    return format;
  }
  public void setFormat(String format) {
    this.format = format;
  }
  public String getV() {
    return v;
  }
  public void setV(String v) {
    this.v = v;
  }
  public String getPartner_id() {
    return partner_id;
  }
  public void setPartner_id(String partner_id) {
    this.partner_id = partner_id;
  }
  public String getTarget_app_key() {
    return target_app_key;
  }
  public void setTarget_app_key(String target_app_key) {
    this.target_app_key = target_app_key;
  }
  public Boolean getSimplify() {
    return simplify;
  }
  public void setSimplify(Boolean simplify) {
    this.simplify = simplify;
  }
  public String getSign_method() {
    return sign_method;
  }
  public void setSign_method(String sign_method) {
    this.sign_method = sign_method;
  }
  public String getSign() {
    return sign;
  }
  public void setSign(String sign) {
    this.sign = sign;
  }
  
  
  
  
  
  
}
