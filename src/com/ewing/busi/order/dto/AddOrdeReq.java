package com.ewing.busi.order.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 订单详情页请求参数
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月8日
 * 
 */
public class AddOrdeReq implements Serializable {


    private Integer resourceId;

    private Integer priceId;

    private Integer count;

    public Integer getResourceId() {
      return resourceId;
    }

    public void setResourceId(Integer resourceId) {
      this.resourceId = resourceId;
    }

    public Integer getPriceId() {
      return priceId;
    }

    public void setPriceId(Integer priceId) {
      this.priceId = priceId;
    }

    public Integer getCount() {
      return count;
    }

    public void setCount(Integer count) {
      this.count = count;
    }
}
