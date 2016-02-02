package com.ewing.busi.order.model;

// Generated 2016-2-2 11:30:39 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * 订单退款
 */
public class OrderRefund implements java.io.Serializable {
    
    /**
     * 
     */
    public Integer id;
    
    /**
     * 订单ID,表order主键
     */
    public int orderId;
    
    /**
     * 订单明细ID,表order_detail主键
     */
    public int orderDetailId;
    
    /**
     * 退款类型 0:退货退款 1:仅退款
     */
    public char type;
    
    /**
     * 消费者ID
     */
    public int customerId;
    
    /**
     * 商户用户ID
     */
    public int userId;
    
    /**
     * 资源id，表web_resource主键
     */
    public int resourceId;
    
    /**
     * 退货原因
     */
    public int reasonType;
    
    /**
     * 退货说明
     */
    public int reason;
    
    /**
     * 物流号
     */
    public int cargoNumber;
    
    /**
     * 退款金额
     */
    public float refundMoney;
    
    /**
     * 退款申请状态 0:发起退款 1:待商户确认 2:商户已经收货 3:已经退款 4:用户取消
     */
    public char status;
    
    /**
     * 是否生效 0:没生效 1:生效
     */
    public char iseff;
    
    /**
     * 
     */
    public Date createTime;
    
    /**
     * 
     */
    public Date lastUpdate;


    public OrderRefund() {
    }

    public OrderRefund(int orderId, int orderDetailId, char type, int customerId, int userId,
            int resourceId, int reasonType, int reason, float refundMoney, char status, char iseff,
            Date createTime, Date lastUpdate) {
        this.orderId = orderId;
        this.orderDetailId = orderDetailId;
        this.type = type;
        this.customerId = customerId;
        this.userId = userId;
        this.resourceId = resourceId;
        this.reasonType = reasonType;
        this.reason = reason;
        this.refundMoney = refundMoney;
        this.status = status;
        this.iseff = iseff;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    public OrderRefund(int orderId, int orderDetailId, char type, int customerId, int userId,
            int resourceId, int reasonType, int reason, int cargoNumber, float refundMoney,
            char status, char iseff, Date createTime, Date lastUpdate) {
        this.orderId = orderId;
        this.orderDetailId = orderDetailId;
        this.type = type;
        this.customerId = customerId;
        this.userId = userId;
        this.resourceId = resourceId;
        this.reasonType = reasonType;
        this.reason = reason;
        this.cargoNumber = cargoNumber;
        this.refundMoney = refundMoney;
        this.status = status;
        this.iseff = iseff;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderDetailId() {
        return this.orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public char getType() {
        return this.type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getReasonType() {
        return this.reasonType;
    }

    public void setReasonType(int reasonType) {
        this.reasonType = reasonType;
    }

    public int getReason() {
        return this.reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }

    public int getCargoNumber() {
        return this.cargoNumber;
    }

    public void setCargoNumber(int cargoNumber) {
        this.cargoNumber = cargoNumber;
    }

    public float getRefundMoney() {
        return this.refundMoney;
    }

    public void setRefundMoney(float refundMoney) {
        this.refundMoney = refundMoney;
    }

    public char getStatus() {
        return this.status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public char getIseff() {
        return this.iseff;
    }

    public void setIseff(char iseff) {
        this.iseff = iseff;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
