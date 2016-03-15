package com.ewing.busi.resource.dto;

import com.ewing.common.dto.PageRequest;

public class CategoryProductInfoReq extends PageRequest {
    /**
     * 是否热门
     */
    private Integer isHot;
    /**
     * 商铺ID
     */
    private Integer shopId;
    
    /**
     * 类别web_category id
     */
    private Integer categoryId;

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getCategoryId() {
      return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
      this.categoryId = categoryId;
    }

}
