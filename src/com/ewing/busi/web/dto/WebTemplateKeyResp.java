package com.ewing.busi.web.dto;

import java.io.Serializable;
import java.util.List;

/**
 *  模板KEY，返回匹配的属性
 * 
 * @author tansonlam
 * @createDate 2016年3月3日
 * 
 */
public class WebTemplateKeyResp implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String groupKey;
    private List<WebBlockDto> blockList;
 

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public List<WebBlockDto> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<WebBlockDto> blockList) {
        this.blockList = blockList;
    }

}
