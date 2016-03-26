package com.ewing.busi.order.dto;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 提交购物车请求参数
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月6日
 * 
 */
public class SubmitRefundExpressReq implements Serializable {
  /**
   * 退货id
   */
  private Integer refundId;

  /**
   * 快递订单号
   */
  private String cargoNum;

  /**
   * 快递公司
   */
  private String cargoCom;

  public Integer getRefundId() {
    return refundId;
  }

  public void setRefundId(Integer refundId) {
    this.refundId = refundId;
  }

  public String getCargoNum() {
    return cargoNum;
  }

  public void setCargoNum(String cargoNum) {
    this.cargoNum = cargoNum;
  }

  public String getCargoCom() {
    return cargoCom;
  }

  public void setCargoCom(String cargoCom) {
    this.cargoCom = cargoCom;
  }



}
