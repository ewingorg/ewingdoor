package com.ewing.busi.order.dto;

import java.io.Serializable;

/**
 * 订单详情页请求参数
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年2月8日
 *
 */
public class OrderInfoDetailResp implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 资源id，表web_resource主键
     */
    private Integer resourceId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * icon url
     */
    private String icon;

    /**
     * 产品数量
     */
    private Integer itemCount;

    /**
     * 产品单价
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public float getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice(float cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

}