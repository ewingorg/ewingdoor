package com.ewing.busi.resource.dto;

/**
 * 商店产品分类
 * 
 * @author tansonlam
 * @createDate 2016年2月29日
 * 
 */
public class CategoryResp implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
