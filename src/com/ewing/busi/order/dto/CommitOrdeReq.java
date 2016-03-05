package com.ewing.busi.order.dto;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 订单详情页请求参数
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月8日
 */
public class CommitOrdeReq implements Serializable {

  /**
   * 付款方式
   */
  private Integer payWayId;

  /**
   * 地址id
   */
  private Integer addrId;



  private List<Item> list;


  public List<Item> getList() {
    return list;
  }

  public void setList(List<Item> list) {
    this.list = list;
  }

  public Integer getPayWayId() {
    return payWayId;
  }


  public void setPayWayId(Integer payWayId) {
    this.payWayId = payWayId;
  }


  public Integer getAddrId() {
    return addrId;
  }


  public void setAddrId(Integer addrId) {
    this.addrId = addrId;
  }


  public CommitOrdeReq() {
    super();
    this.list = Lists.newArrayList();
  }



  public class Item {
    private Integer detailId;

    private Integer itemCount;

    public Integer getDetailId() {
      return detailId;
    }

    public void setDetailId(Integer detailId) {
      this.detailId = detailId;
    }

    public Integer getItemCount() {
      return itemCount;
    }

    public void setItemCount(Integer itemCount) {
      this.itemCount = itemCount;
    }
  }
}
