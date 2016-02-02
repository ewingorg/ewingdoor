package com.ewing.busi.customer.model;

// Generated 2016-2-2 16:23:42 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * CustomerThirdAccount generated by hbm2java
 */
public class CustomerThirdAccount implements java.io.Serializable {

    private Integer id;
    /**
     * 消费者ID
     */
    private int customerId;
    /**
     * 第三方账号接入平台(1-微信,2-淘宝)
     */
    private int thirdPlatform;
    /**
     * 第三方账号ID
     */
    private String userId;
    /**
     * 第三方账号昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String headIcon;
    private Long createTime;
    private Date lastUpdate;

    public CustomerThirdAccount() {
    }

    public CustomerThirdAccount(int customerId, int thirdPlatform, String userId, Date lastUpdate) {
        this.customerId = customerId;
        this.thirdPlatform = thirdPlatform;
        this.userId = userId;
        this.lastUpdate = lastUpdate;
    }

    public CustomerThirdAccount(int customerId, int thirdPlatform, String userId, String nickName,
            String headIcon, Long createTime, Date lastUpdate) {
        this.customerId = customerId;
        this.thirdPlatform = thirdPlatform;
        this.userId = userId;
        this.nickName = nickName;
        this.headIcon = headIcon;
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

    public int getThirdPlatform() {
        return this.thirdPlatform;
    }

    public void setThirdPlatform(int thirdPlatform) {
        this.thirdPlatform = thirdPlatform;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadIcon() {
        return this.headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
