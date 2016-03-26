package com.ewing.busi.order.dto;

/**
 * 提交退货申请
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年3月23日
 */
public class SubmitRefundReq {
  
  private Integer orderDetailId;
  
  /**
   * 退款类型
   */
  private String refundType;
  
  /**
   * 原因类型
   */
  private Integer reasonType;
  
  /**
   * 原因
   */
  private String reason;
  
  /**
   * 证明图片
   */
  private String picUrl;
  
  /**
   * 金额
   */
  private String money;
  
  
  

  public Integer getOrderDetailId() {
    return orderDetailId;
  }

  public void setOrderDetailId(Integer orderDetailId) {
    this.orderDetailId = orderDetailId;
  }

  public String getRefundType() {
    return refundType;
  }

  public void setRefundType(String refundType) {
    this.refundType = refundType;
  }

  public Integer getReasonType() {
    return reasonType;
  }

  public void setReasonType(Integer reasonType) {
    this.reasonType = reasonType;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  public String getMoney() {
    return money;
  }

  public void setMoney(String money) {
    this.money = money;
  }
  
}
