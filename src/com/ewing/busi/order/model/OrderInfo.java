package com.ewing.busi.order.model;

// Generated 2016-2-2 11:30:39 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * 订单
 */
public class OrderInfo implements java.io.Serializable {

    private Integer id;
    /**
     * 流水号
     */
    private String bizId;
    /**
     * 消费者ID
     */
    private int customerId;
    /**
     * 商户用户ID
     */
    private int userId;
    /**
     * 产品费用
     */
    private float productPrice;
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
     * 收件人
     */
    private String receiver;
    /**
     * 邮编
     */
    private String postCode;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区
     */
    private String region;
    /**
     * 具体位置
     */
    private String address;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 是否生效 0:没生效 1:生效
     */
    private char iseff;
    private Date createTime;
    private Date lastUpdate;

    public OrderInfo() {
    }

    public OrderInfo(int customerId, int userId, float productPrice, float cargoPrice,
            float totalPrice, char status, String phone, char iseff, Date createTime,
            Date lastUpdate) {
        this.customerId = customerId;
        this.userId = userId;
        this.productPrice = productPrice;
        this.cargoPrice = cargoPrice;
        this.totalPrice = totalPrice;
        this.status = status;
        this.phone = phone;
        this.iseff = iseff;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    public OrderInfo(int customerId, int userId, float productPrice, float cargoPrice,
            float totalPrice, char status, String receiver, String postCode, String province,
            String city, String region, String address, String phone, char iseff, Date createTime,
            Date lastUpdate) {
        this.customerId = customerId;
        this.userId = userId;
        this.productPrice = productPrice;
        this.cargoPrice = cargoPrice;
        this.totalPrice = totalPrice;
        this.status = status;
        this.receiver = receiver;
        this.postCode = postCode;
        this.province = province;
        this.city = city;
        this.region = region;
        this.address = address;
        this.phone = phone;
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

    public float getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
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

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
