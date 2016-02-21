package com.ewing.busi.order.dto;

import java.io.Serializable;

import com.ewing.core.jdbc.annotation.Column;

/**
 * 订单详情页请求参数
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月8日
 *
 */
public class OrderInfoDetailResp implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Column(fieldName = "id")
    private Integer id;
    /**
     * 订单id
     */
    @Column(fieldName = "order_id")
    private Integer orderId;
    /**
     * 资源id，表web_resource主键
     */
    @Column(fieldName = "resource_id")
    private Integer resourceId;

    /**
     * 产品名称
     */
    @Column(fieldName = "name")
    private String productName;

    /**
     * icon url
     */
    @Column(fieldName = "image_url")
    private String icon;

    /**
     * 产品数量
     */
    @Column(fieldName = "item_count")
    private Integer itemCount;

    /**
     * 产品单价
     */
    @Column(fieldName = "unit_price")
    private String unitPrice;

    /**
     * 运费
     */
    @Column(fieldName = "cargo_price")
    private String cargoPrice;

    /**
     * 总价
     */
    @Column(fieldName = "total_price")
    private String totalPrice;

    /**
     * 购物状态 0:待付款 1:待发货 2:待收货
     */
    @Column(fieldName = "status")
    private String status;

    @Column(fieldName = "shop_name")
    private String shopName = "海鲜街";

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
    
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice(String cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}