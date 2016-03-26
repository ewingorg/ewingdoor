package com.ewing.busi.order.dto;

import java.io.Serializable;

/**
 * 申请退货
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月6日
 */
public class ApplyRefundtReq{

    /**
     * 添加的资源id
     */
    private Integer orderDetailid;

    public Integer getOrderDetailid() {
      return orderDetailid;
    }

    public void setOrderDetailid(Integer orderDetailid) {
      this.orderDetailid = orderDetailid;
    }

    
   
}
