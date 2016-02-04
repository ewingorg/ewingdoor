package com.ewing.busi.customer.dto;

import java.io.Serializable;

/**
 * 
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年2月4日
 *
 */
public class AddressAddDto implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 消费者ID
     */
    private int customerId;
    
    /**
     * 收件人
     */
    private String receiver;

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
     * 邮编
     */
    private String postCode;

    /**
     * 是否默认 0:不是 1:是
     */
    private char isDefault;

    /**
     * 是否有效 0 无效 1有效
     */
    private char iseff;

    
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public char getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(char isDefault) {
        this.isDefault = isDefault;
    }

    public char getIseff() {
        return iseff;
    }

    public void setIseff(char iseff) {
        this.iseff = iseff;
    }
}
