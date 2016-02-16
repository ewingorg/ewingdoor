package com.ewing.busi.order.dto;

// Generated 2016-2-2 17:07:58 by Hibernate Tools 3.4.0.CR1


/**
 * OrderCart generated by hbm2java
 */
public class LightOrderCartResp implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer resourceId;
    /**
     * 数量
     */
    private Integer itemCount;
    /**
     * 单价
     */
    private float unitPrice;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 头像
     */
    private String icon;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}