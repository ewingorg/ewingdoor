package com.ewing.busi.order.dto;

/**
 * 提交退货申请
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年3月23日
 */
public class SubmitRefundReq {

  /**
   * 资源id
   */
  private Integer resourceId;

  /**
   * order_detail id
   */
  private Integer orderDetailId;

  /**
   * 退款类型 0:退货退款 1:仅退款
   */
  private Integer type;

  /**
   * 退货原因
   */
  private Integer reasonType;

  /**
   * 退货说明
   */
  private String reason;

  public Integer getResourceId() {
    return resourceId;
  }

  public void setResourceId(Integer resourceId) {
    this.resourceId = resourceId;
  }

  public Integer getOrderDetailId() {
    return orderDetailId;
  }

  public void setOrderDetailId(Integer orderDetailId) {
    this.orderDetailId = orderDetailId;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
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

  

}
