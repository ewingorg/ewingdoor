package com.ewing.busi.express.dto;

public class ExpressResp {
  
  /**
   * 快递实时时间
   */
  public String time; 
  
  /**
   * 快递实时信息
   */
  public String msg;

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  
}
