package com.ewing.busi.order.dto;

/**
 * 订单列表的入参
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月13日
 *
 */
public class LightOrderInfoReq {
    /**
     * 状态  0:待付款 1:待发货 2:待收货',
     */
    private String status;
    
    /**
     * page
     */
    private Integer page;
    
    /**
     * pageSize
     */
    private Integer pageSize;
    
    
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
