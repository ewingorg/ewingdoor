package com.ewing.busi.web.dto;

/**
 * 模板KEY请求
 * 
 * @author tansonlam
 * @createDate 2016年3月3日
 * 
 */
public class WebTemplateKeyReq {
    /**
     * 商铺ID
     */
    private Integer shopId;
    /**
     * 模板名称
     */
    private String templateName;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

}
