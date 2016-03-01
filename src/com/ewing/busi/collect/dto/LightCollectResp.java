package com.ewing.busi.collect.dto;

import java.io.Serializable;

import com.ewing.core.jdbc.annotation.Column;

/**
 * 收藏夹列表展示dto
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月23日
 */
public class LightCollectResp implements Serializable{
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    /**
     * 客户Id
     */
    @Column(fieldName = "id")
    private Integer id;

    /**
     * 资源id
     */
    @Column(fieldName = "resource_id")
    private Integer resourceId;

    /**
     * 商品名称
     */
    @Column(fieldName = "name")
    private String productName;
    /**
     * 单价
     */
    @Column(fieldName = "price")
    private String unitPrice;

    /**
     * 数量
     */
    @Column(fieldName = "stock_num")
    private String stockNum;
    
    /**
     * 商品头像
     */
    @Column(fieldName = "image_url")
    private String icon;

    /**
     * page
     */
    @Column(fieldName = "page")
    private Integer page;

    /**
     * pageSize
     */
    @Column(fieldName = "pageSize")
    private Integer pageSize;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
