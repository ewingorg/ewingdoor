package com.ewing.common.dto;

/**
 * 页面请求參數
 * 
 * @author tansonlam
 * @createDate 2016年1月26日
 * 
 */
public class PageRequest {
    private Integer page;
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

}
