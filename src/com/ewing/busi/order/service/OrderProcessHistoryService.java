package com.ewing.busi.order.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.constants.OrderStatus;
import com.ewing.busi.order.constants.ProcessHistoryType;
import com.ewing.busi.order.constants.RefundStatus;
import com.ewing.busi.order.dao.OrderProcessHistoryDao;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.busi.order.model.OrderProcessHistory;
import com.ewing.busi.order.model.OrderRefund;
import com.ewing.core.jdbc.BaseDao;

@Repository("orderProcessHistoryService")
public class OrderProcessHistoryService {
  
  @Resource
  private BaseDao baseDao;
  
  @Resource
  private OrderProcessHistoryDao orderProcessHistoryDao;
  
  
  public boolean addOrderHistory(OrderInfo orderInfo, OrderStatus status){
    if(null == orderInfo || null == status){
      return false;
    }
    
    OrderProcessHistory history = new OrderProcessHistory();
    history.setBizId(orderInfo.getBizId());
    history.setBusiType(ProcessHistoryType.ORDER.getValue());
    history.setBusiId(orderInfo.getId());
    history.setCustomerId(orderInfo.getCustomerId());
    history.setUserId(orderInfo.getUserId());
    history.setStatus(status.getValue());
    history.setStatusString(status.getMsg());
    baseDao.save(history);
    
    return true;
  }
  
  public boolean addRefundHistory(OrderRefund refund, RefundStatus status){
    if(null == refund || null == status){
      return false;
    }
    
    OrderProcessHistory history = new OrderProcessHistory();
    history.setBizId(refund.getBizId());
    history.setBusiType(ProcessHistoryType.ORDER.getValue());
    history.setBusiId(refund.getId());
    history.setCustomerId(refund.getCustomerId());
    history.setUserId(refund.getUserId());
    history.setStatus(status.getValue());
    history.setStatusString(status.getMsg());
    baseDao.save(history);
    
    return true;
  }
  
  

}
