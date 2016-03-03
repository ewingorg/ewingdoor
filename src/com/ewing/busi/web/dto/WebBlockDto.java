package com.ewing.busi.web.dto;

import java.io.Serializable;

/**
 * web模板分组配置屬性
 * 
 * @author tansonlam
 * @createDate 2016年3月3日
 * 
 */
public class WebBlockDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String groupKey;
    private String imageUrl;
    private String linkUrl;
    private String name;
    private Integer parentId;
    private Integer rank;
    private String shortDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

}
