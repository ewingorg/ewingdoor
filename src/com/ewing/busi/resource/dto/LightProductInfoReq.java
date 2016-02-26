package com.ewing.busi.resource.dto;

import com.ewing.common.dto.PageRequest;

public class LightProductInfoReq extends PageRequest {
    /**
     * 是否热门
     */
    private Integer isHot;

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

}
