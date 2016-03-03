package com.ewing.busi.customer.model;

// Generated 2016-2-2 17:07:58 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * CustomerAddress generated by hbm2java
 */
public class CustomerAddress implements java.io.Serializable {

    private Integer id;
    private Integer customerId;
    private String receiver;
    private String province;
    private String city;
    private String region;
    private String address;
    private String phone;
    private String postCode;
    private String isDefault;
    private String iseff;
    private Date createTime;
    private Date lastUpdate;

    public CustomerAddress() {
    }

    public CustomerAddress(int customerId, String phone, Date createTime, Date lastUpdate) {
        this.customerId = customerId;
        this.phone = phone;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    public CustomerAddress(Integer customerId, String receiver,String province, String city, String region,
            String address, String phone, String postCode, String isDefault, String iseff,
            Date createTime, Date lastUpdate) {
        this.customerId = customerId;
        this.receiver = receiver;
        this.province = province;
        this.city = city;
        this.region = region;
        this.address = address;
        this.phone = phone;
        this.postCode = postCode;
        this.isDefault = isDefault;
        this.iseff = iseff;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getPostCode() {
        return this.postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIseff() {
        return this.iseff;
    }

    public void setIseff(String iseff) {
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

    @Override
    public String toString() {
      return "CustomerAddress [id=" + id + ", customerId=" + customerId + ", receiver=" + receiver
          + ", province=" + province + ", city=" + city + ", region=" + region + ", address="
          + address + ", phone=" + phone + ", postCode=" + postCode + ", isDefault=" + isDefault
          + ", iseff=" + iseff + ", createTime=" + createTime + ", lastUpdate=" + lastUpdate + "]";
    }
    
    

}
