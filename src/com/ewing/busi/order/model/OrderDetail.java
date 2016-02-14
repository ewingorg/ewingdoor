package com.ewing.busi.order.model;

// Generated 2016-2-2 17:07:58 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * 订单明细
 */
public class OrderDetail implements java.io.Serializable {

    private Integer id;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 客户id
     */
    private Integer customerId;
    /**
     * 流水号
     */
    private String bizId;
    /**
     * 商家id
     */
    private Integer userId;
    /**
     * 资源id，表web_resource主键
     */
    private Integer resourceId;
    /**
     * 产品数量
     */
    private Integer itemCount;
    /**
     * 产品单价s
     */
    private float unitPrice;
    /**
     * 运费
     */
    private float cargoPrice;
    /**
     * 总价
     */
    private float totalPrice;
    /**
     * 购物状态 0:待付款 1:待发货 2:待收货
     */
    private char status;
    private char iseff;
    private Date createTime;
    private Date lastUpdate;

    public OrderDetail() {
    }

    public OrderDetail(Integer orderId, Integer customerId, Integer userId, Integer resourceId, Integer itemCount,
            Integer unitPrice, float cargoPrice, float totalPrice, char status, char iseff,
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

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getItemCount() {
        return this.itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public float getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
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
