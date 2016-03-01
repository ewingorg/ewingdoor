package com.ewing.busi.order.dto;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 提交购物车请求参数
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月6日
 *
 */
public class AddCartReq implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 添加的资源id
     */
    private Integer resourceId;
    
    /**
     * web_resouce_price 表的id
     */
    private Integer priceId;
    
    /**
     * 数量
     */
    private Integer count;

    
    public Integer getPriceId() {
      return priceId;
    }

    public void setPriceId(Integer priceId) {
      this.priceId = priceId;
    }

    public Integer getResourceId() {
      return resourceId;
    }

    public void setResourceId(Integer resourceId) {
      this.resourceId = resourceId;
    }

    public Integer getCount() {
      return count;
    }

    public void setCount(Integer count) {
      this.count = count;
    }
    

}
