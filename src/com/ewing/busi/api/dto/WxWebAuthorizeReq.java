package com.ewing.busi.api.dto;

public class WxWebAuthorizeReq {
  
  public WxWebAuthorizeReq() {
    super();
  }

  public WxWebAuthorizeReq(String code, String state) {
    super();
    this.code = code;
    this.state = state;
  }

  /**
   * code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
   */
  private String code;

  private String state;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
