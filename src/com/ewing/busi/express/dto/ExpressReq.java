package com.ewing.busi.express.dto;

public class ExpressReq {
  
  /**
   * 订单号
   */
  public String num; 
  
  /**
   * 快递公司代号
   */
  public String com;

  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
  }

  public String getCom() {
    return com;
  }

  public void setCom(String com) {
    this.com = com;
  }

  
}
