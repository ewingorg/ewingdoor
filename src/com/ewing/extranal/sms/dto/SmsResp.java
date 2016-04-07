package com.ewing.extranal.sms.dto;

public class SmsResp {
  
  /**
   * 错误码
   */
  private String err_code; 
  /**
   * 134523^4351232返回结果
   */
  private String model;
  /**
   * true表示成功，false表示失败
   */
  private String success;
  /**
   * 返回信息描述
   */
  private String msg;
  public String getErr_code() {
    return err_code;
  }
  public void setErr_code(String err_code) {
    this.err_code = err_code;
  }
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
  }
  public String getSuccess() {
    return success;
  }
  public void setSuccess(String success) {
    this.success = success;
  }
  public String getMsg() {
    return msg;
  }
  public void setMsg(String msg) {
    this.msg = msg;
  }
  
  
  

}
