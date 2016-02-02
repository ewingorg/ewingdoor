package com.ewing.busi.order.model;

// Generated 2016-2-2 11:30:39 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * 购物车
 */
public class OrderCart implements java.io.Serializable {

    private Integer id;
    
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
     * 件数
     */
    private int itemCount;
    /**
     * 单价价格
     */
    private float unitPrice;
    /**
     * 单价价格
     */
    private float totalPrice;
    /**
     * 是否生效 0:没生效 1:生效
     */
    private char iseff;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date lastUpdate;

    public OrderCart() {
    }

    public OrderCart(int customerId, int userId, int resourceId, int itemCount, float unitPrice,
            float totalPrice, char iseff, Date createTime, Date lastUpdate) {
        this.customerId = customerId;
        this.userId = userId;
        this.resourceId = resourceId;
        this.itemCount = itemCount;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.iseff = iseff;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public float getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
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
