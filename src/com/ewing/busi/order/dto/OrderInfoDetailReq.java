package com.ewing.busi.order.dto;

import java.io.Serializable;

/**
 * 订单详情页请求参数
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月8日
 *
 */
public class OrderInfoDetailReq implements Serializable{
    /**
     * 客户Id
     */
    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
}
