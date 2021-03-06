package com.ewing.busi.customer.dto;

import java.io.Serializable;

/**
 * 
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
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
    private Integer customerId;
    
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
    private String isDefault;

    /**
     * 是否有效 0 无效 1有效
     */
    private String iseff;

    
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
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

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIseff() {
        return iseff;
    }

    public void setIseff(String iseff) {
        this.iseff = iseff;
    }
}
