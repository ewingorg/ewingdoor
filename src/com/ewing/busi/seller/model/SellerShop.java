package com.ewing.busi.seller.model;

// Generated 2016-2-2 11:30:39 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * 
 * 
 */
public class SellerShop implements java.io.Serializable {
    private Integer id;
    private Integer userId;
    private String shopName;
    private String shopIcon;
    private String shopDesc;
    private String email;
    private String phone;
    private String addr;
    private String iseff;
    private Date createTime;
    private Date lastUpdate;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getShopIcon() {
        return shopIcon;
    }
    public void setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
    }
    public String getShopDesc() {
        return shopDesc;
    }
    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getIseff() {
        return iseff;
    }
    public void setIseff(String iseff) {
        this.iseff = iseff;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

     
}
