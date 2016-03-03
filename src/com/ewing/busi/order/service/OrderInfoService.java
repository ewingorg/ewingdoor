package com.ewing.busi.order.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import com.ewing.busi.order.constants.OrderStatus;
import com.ewing.busi.order.dao.OrderInfoDao;
import com.ewing.busi.order.dto.AddOrdeReq;
import com.ewing.busi.order.dto.ConfirmOrderReq;
import com.ewing.busi.order.dto.ConfirmOrderReq.Item;
import com.ewing.busi.order.dto.LightOrderInfoResp;
import com.ewing.busi.order.dto.OrderInfoDetailResp;
import com.ewing.busi.order.helper.OrderHelper;
import com.ewing.busi.order.model.OrderDetail;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.busi.resource.dao.WebResourceDao;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.busi.resource.model.WebResourcePrice;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.common.constants.IsEff;
import com.ewing.common.constants.ResponseCode;
import com.ewing.common.exception.BusinessException;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.utils.BizGenerator;
import com.google.common.collect.Lists;

@Repository("orderInfoService")
public class OrderInfoService {

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
  /**
   * 根据客户id查询 购物车列表
   * 
   * @param cusId 客户ID
   * @param status 订单状态
   */
  public List<LightOrderInfoResp> queryByCusId(Integer cusId, String status, Integer page,
      Integer pageSize) {
    Validate.notNull(cusId, "客户id不能为空");
    Validate.notNull(page, "page不能为空");
    Validate.notNull(pageSize, "pageSize不能为空");

    List<LightOrderInfoResp> dtoList = Lists.newArrayList();
    List<OrderInfo> orderList = orderInfoDao.queryByCusId(cusId, status, page, pageSize);
    for (OrderInfo order : orderList) {
      List<OrderInfoDetailResp> detailDtoList =
          orderDetailService.findByOrderIdAndCusId(order.getId(), cusId);
      if (CollectionUtils.isEmpty(detailDtoList)) {
        continue;
      }

      dtoList.add(new LightOrderInfoResp(order, detailDtoList));
    }

    return dtoList;
  }

  /**
   * 
   * @param orderId
   * @param cusId 商户Id
   * @author Joeson
   */
  public List<OrderInfoDetailResp> getByIdAndCusId(Integer orderId, Integer cusId) {
    Validate.notNull(orderId, "orderId不能为空");

    return orderDetailService.findByOrderIdAndCusId(orderId, cusId);
  }

  /**
   * 确认订单
   * 
   * @param req
   * @author Joeson
   */
  public void confirmOrder(ConfirmOrderReq req) {
    Validate.notNull(req, "入参不能为空");

    Integer orderId = null;
    float totalPrice = 0f;
    for (Item item : req.getList()) {
        OrderDetail detail = baseDao.findOne(item.getDetailId(), OrderDetail.class);
        detail.setItemCount(item.getItemCount());
        detail.setStatus(OrderStatus.WAIT_PAY.getValue());
        detail.setTotalPrice(OrderHelper.analysyTotal(detail));
        totalPrice = totalPrice + detail.getTotalPrice();

        orderId = detail.getOrderId();
        baseDao.update(detail);
    }

      OrderInfo order = baseDao.findOne(orderId, OrderInfo.class);
      order.setTotalPrice(totalPrice);
      order.setStatus(OrderStatus.WAIT_PAY.getValue());
      baseDao.update(order);
  }

  /**
   * 取消order,如果order为已付款、待
   * 
   * @param orderId
   * @author Joeson
   */
  public void cancelOrder(Integer orderId) {
    Validate.notNull(orderId, "orderId不能为空");

    OrderInfo order = baseDao.findOne(orderId, OrderInfo.class);
    Validate.notNull(order, "order不能为空");
    // 如果状态不为待付款或已收货，不能关闭订单
    if (!(ObjectUtils.equals(order.getStatus(), OrderStatus.WAIT_PAY.getValue()) || ObjectUtils
        .equals(order.getStatus(), OrderStatus.FINISHED.getValue()))) {
      throw new BusinessException(ResponseCode.BIZ_STATUS_ERROR, "当前状态不能关闭订单");
    }

    order.setStatus(OrderStatus.CLOSED.getValue());
    baseDao.update(order);

    return;
  }

  /**
   * 添加到order，场景：立即订购
   * @param cusId 客户id
   * @param req 请求参数
   * @author Joeson
   * @throws Exception 
   */
  public Integer addOrder(Integer userId, Integer cusId, AddOrdeReq req) throws Exception {
    Validate.notNull(req, "入参不能为空");
    Validate.notNull(req.getResourceId(), "resourceId不能为空");
    Validate.notNull(req.getPriceId(), "priceId不能为空");
    Validate.notNull(req.getCount(), "count不能为空");
    
    Integer resourceId = req.getResourceId();
    Integer priceId = req.getPriceId();
    Integer count = req.getCount();

    WebResource resource = baseDao.findOne(resourceId, WebResource.class);
    if (null == resource) {
      throw new Exception(String.format("没有找到对应的资源[id=%d]", resourceId));
    }
    WebResourcePrice price = baseDao.findOne(priceId, WebResourcePrice.class);
    if(null == price || price.getResourceId() != resourceId){
      throw new Exception(String.format("价格异常[id=%d]", priceId));
    }

    String bizId = BizGenerator.generateBizNum();
    //保存orderinfo
    OrderInfo orderInfo = new OrderInfo();
    orderInfo.setCustomerId(cusId);
    orderInfo.setUserId(userId);
    orderInfo.setBizId(bizId);
    orderInfo.setCargoPrice(0f);
    orderInfo.setTotalPrice(0f);
    orderInfo.setStatus(OrderStatus.WAIT_PAY.getValue());
    orderInfo.setPhone("");
    orderInfo.setIseff(IsEff.EFFECTIVE.getValue());
    baseDao.save(orderInfo);
    
    OrderDetail detail = OrderHelper.initOrderDetail(orderInfo.getId(), userId, cusId, bizId, count, resource, price);
    baseDao.save(detail);
    
    return orderInfo.getId();
  }
}
