package com.ewing.busi.customer.dto;

public class LightAddressInfoReq {
    /**
     * 客户Id
     */
    private Integer cusId;
    
    /**
     * page
     */
    private Integer page;
    
    /**
     * pageSize
     */
    private Integer pageSize;
    
    

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

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    
}
