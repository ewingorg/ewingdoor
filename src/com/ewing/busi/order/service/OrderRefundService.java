package com.ewing.busi.order.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import com.ewing.busi.customer.service.CustomerAddressService;
import com.ewing.busi.order.constants.OrderStatus;
import com.ewing.busi.order.constants.RefundStatus;
import com.ewing.busi.order.dao.OrderInfoDao;
import com.ewing.busi.order.dto.LightOrderInfoResp;
import com.ewing.busi.order.dto.SubmitRefundExpressReq;
import com.ewing.busi.order.dto.SubmitRefundReq;
import com.ewing.busi.order.model.OrderDetail;
import com.ewing.busi.order.model.OrderRefund;
import com.ewing.busi.resource.dao.WebResourceDao;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.utils.BizGenerator;

@Repository("orderRefundService")
public class OrderRefundService {

  @Resource
  private BaseDao baseDao;
  @Resource
  private OrderInfoDao orderInfoDao;
  @Resource
  private OrderDetailService orderDetailService;
  @Resource
  private WebResourceService webResourceService;
  @Resource
  private WebResourceDao webResoueceDao;
  @Resource
  private CustomerAddressService customerAddressService;
  @Resource
  private OrderProcessHistoryService orderProcessHistoryService;
  
  /***
   * 提交退货申请
   * @param cusId
   * @param req
   * @author Joeson
   */
  public Integer submitRefund(Integer cusId, SubmitRefundReq req) {
    Validate.notNull(req, "入参不能为空");
    
    OrderDetail detail = orderDetailService.findByIdAndCusId(req.getOrderDetailId(),  cusId);
    Validate.notNull(detail,"找不到对应的orderdetail");
        
    OrderRefund refund = new OrderRefund();
    refund.setOrderId(detail.getOrderId());
    refund.setBizId(BizGenerator.generateBizNum());
    refund.setOrderDetailId(req.getOrderDetailId());
    refund.setType(req.getRefundType());
    refund.setCustomerId(cusId);
    refund.setUserId(detail.getUserId());
    refund.setResourceId(detail.getResourceId());
    refund.setReasonType(req.getReasonType());
    refund.setReason(req.getReason());
    refund.setRefundMoney(detail.getTotalPrice());
    refund.setProvePicUrl(req.getPicUrl());
    refund.setStatus(RefundStatus.WAIT_CONFIRM.getValue());
    refund.setIseff(IsEff.EFFECTIVE.getValue());
    refund.setCreateTime(new Date());
    baseDao.save(refund);
    
    detail.setStatus(OrderStatus.REFUND.getValue());
    baseDao.update(detail);
    
    //记录流程记录
    orderProcessHistoryService.addRefundHistory(refund, RefundStatus.WAIT_CONFIRM);
    
    return detail.getOrderId();
  }

  /**
   * 提交退款快递(快递公司，快递号)
   * @param cusId
   * @param req
   * @author Joeson
   */
  public boolean submitRefundExpress(Integer cusId, SubmitRefundExpressReq req) {
    Validate.notNull(req, "入参不能为空");
    
    OrderRefund refund = baseDao.findOne(req.getRefundId(), OrderRefund.class);
    Validate.notNull(refund, String.format("退货记录找不到[refundId=%d]", req.getRefundId()));
    
    refund.setCargoNumber(req.getCargoNum());
    refund.setCargoCom(req.getCargoCom());
    refund.setStatus(RefundStatus.WAIT_RECEIVED.getValue()); //更新退货流程为等待收货
    baseDao.update(refund);
    
    //记录流程记录
    orderProcessHistoryService.addRefundHistory(refund, RefundStatus.WAIT_RECEIVED);
    
    return true;
  }
  
  
}
