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
     * 客户Id
     */
    private Integer cusId;
    
    /**
     * 状态  0:待付款 1:待发货 2:待收货',
     */
    private Character status;
    
    /**
     * page
     */
    private Integer page;
    
    /**
     * pageSize
     */
    private Integer pageSize;
    
    
    

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
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

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    
}
