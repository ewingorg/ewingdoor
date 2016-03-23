package com.ewing.busi.order.dto;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 申请退货
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月6日
 */
public class ApplyRefundtReq implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 添加的资源id
     */
    private Integer orderId;

    public ApplyRefundtReq(Integer orderId) {
      super();
      this.orderId = orderId;
    }

    public Integer getOrderId() {
      return orderId;
    }

    public void setOrderId(Integer orderId) {
      this.orderId = orderId;
    }
}
