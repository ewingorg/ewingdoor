package com.ewing.busi.order.model;

// Generated 2016-2-2 11:30:39 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * 订单明细
 */
public class OrderDetail implements java.io.Serializable {

    private Integer id;
    /**
     * 流水号
     */
    private String bizId;
    /**
     * 订单ID
     */
    private int orderId;
    /**
     * 消费者ID
     */
    private int customerId;
    /**
     * 商户用户ID
     */
    private int userId;
    /**
     * 资源id，表web_resource主键
     */
    private int resourceId;
    /**
     * 产品数量
     */
    private int itemCount;
    /**
     * 产品单价
     */
    private int unitPrice;
    /**
     * 运费
     */
    private float cargoPrice;
    /**
     * 总费用
     */
    private float totalPrice;
    /**
     * 购物状态 0:待付款 1:待发货 2:待收货
     */
    private char status;
    /**
     * 是否生效 0:没生效 1:生效
     */
    private char iseff;
    private Date createTime;
    private Date lastUpdate;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int customerId, int userId, int resourceId, int itemCount,
            int unitPrice, float cargoPrice, float totalPrice, char status, char iseff,
            Date createTime, Date lastUpdate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.userId = userId;
        this.resourceId = resourceId;
        this.itemCount = itemCount;
        this.unitPrice = unitPrice;
        this.cargoPrice = cargoPrice;
        this.totalPrice = totalPrice;
        this.status = status;
        this.iseff = iseff;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    
    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getItemCount() {
        return this.itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getCargoPrice() {
        return this.cargoPrice;
    }

    public void setCargoPrice(float cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public float getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
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
