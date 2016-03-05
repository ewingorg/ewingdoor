package com.ewing.busi.order.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.ewing.busi.order.constants.OrderStatus;

/**
 * 订单列表的入参
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月13日
 * 
 */
public class LightOrderInfoReq {
  /**
   * 状态 0:待付款 1:待发货 2:待收货',
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

  /**
   * 将页面传参转化为数据库值
   * 
   * @return
   * @author Joeson
   */
  public List<String> convertStatus() {
    if (StringUtils.isEmpty(status)) {
      return Arrays.asList(new String[] {OrderStatus.WAIT_PAY.getValue(),
          OrderStatus.WAIT_SEND.getValue(), OrderStatus.WAIT_RECEIVE.getValue(),
          OrderStatus.REFUND.getValue(), OrderStatus.FINISHED.getValue(),
          OrderStatus.CLOSED.getValue()});
    } else {
      return Arrays.asList(status.toString());
    }
  }



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
