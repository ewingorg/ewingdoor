package com.ewing.busi.order.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import com.ewing.busi.customer.dto.AddressDetailResp;
import com.ewing.busi.customer.service.CustomerAddressService;
import com.ewing.busi.order.constants.OrderStatus;
import com.ewing.busi.order.dao.OrderInfoDao;
import com.ewing.busi.order.dto.AddOrdeReq;
import com.ewing.busi.order.dto.CommitOrdeReq;
import com.ewing.busi.order.dto.ConfirmOrderReq;
import com.ewing.busi.order.dto.ConfirmOrderReq.Item;
import com.ewing.busi.order.dto.LightOrderInfoResp;
import com.ewing.busi.order.dto.OrderInfoDetailResp;
import com.ewing.busi.order.dto.SubmitRefundReq;
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
import com.ewing.utils.IntegerUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
  
  /**
   * 根据客户id查询 购物车列表
   * 
   * @param cusId 客户ID
   * @param statusList 订单状态
   */
  public List<LightOrderInfoResp> queryByCusId(Integer cusId, List<String> statusList, Integer page,
      Integer pageSize) {
    Validate.notNull(cusId, "客户id不能为空");
    Validate.notNull(page, "page不能为空");
    Validate.notNull(pageSize, "pageSize不能为空");

    List<LightOrderInfoResp> dtoList = Lists.newArrayList();
    List<OrderInfo> orderList = orderInfoDao.queryByCusIdAndStatus(cusId, statusList, page, pageSize);
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
   * 取消order,如果order为已付款、待收货，不能关闭订单
   * 
   * @param orderId
   * @author Joeson
   * @param orderId 
   */
  public void closeOrder(Integer cusId, Integer orderId) {
    Validate.notNull(orderId, "orderId不能为空");

    OrderInfo order = baseDao.findOne(orderId, OrderInfo.class);
    Validate.notNull(order, "order不能为空");
    
    if(!IntegerUtils.equals(order.getCustomerId(), cusId)){
      return;
    }
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
   * 
   * 如果priceId为空，则取web_resource中的price
   * @param cusId 客户id
   * @param req 请求参数
   * @author Joeson
   * @throws Exception 
   */
  public Integer addOrder(Integer cusId, AddOrdeReq req) throws Exception {
    Validate.notNull(req, "入参不能为空");
    Validate.notNull(req.getResourceId(), "resourceId不能为空");
    Validate.notNull(req.getCount(), "count不能为空");
    
    Integer resourceId = req.getResourceId();
    Integer priceId = req.getPriceId();
    Integer count = req.getCount();

    WebResource resource = baseDao.findOne(resourceId, WebResource.class);
    if (null == resource) {
      throw new Exception(String.format("没有找到对应的资源[id=%d]", resourceId));
    }
    WebResourcePrice price = null == priceId ? null : baseDao.findOne(priceId, WebResourcePrice.class);

    String bizId = BizGenerator.generateBizNum();
    //保存orderinfo
    OrderInfo orderInfo = new OrderInfo();
    orderInfo.setCustomerId(cusId);
    orderInfo.setUserId(resource.getUserId());
    orderInfo.setBizId(bizId);
    orderInfo.setCargoPrice(0f);
    orderInfo.setTotalPrice(0f);
    orderInfo.setStatus(OrderStatus.INIT.getValue());
    orderInfo.setPhone("");
    orderInfo.setIseff(IsEff.EFFECTIVE.getValue());
    baseDao.save(orderInfo);
    
    OrderDetail detail = OrderHelper.initOrderDetail(orderInfo.getId(), priceId, cusId, bizId, count, resource, price);
    baseDao.save(detail);
    
    return orderInfo.getId();
  }

  /**
   * 提交订单，将订单状态改为待支付状态待支付状态
   * @param cusId
   * @param req
   * @author Joeson
   */
  public boolean commitOrder(Integer cusId, CommitOrdeReq req) {
    Validate.notNull(cusId, "cusId不能为空");
    Validate.notNull(req, "req不能为空");
    Validate.notNull(req.getAddrId(), "addrId不能为空");
    Validate.notNull(req.getPayWayId(), "payway不能为空");
    
    AddressDetailResp address = customerAddressService.findById(req.getAddrId(), cusId);
    List<com.ewing.busi.order.dto.CommitOrdeReq.Item> list = req.getList();
    Map<Integer,Integer> itemMap = Maps.newHashMap();//<key,value> ==> <detailId,itemCount>
    List<Integer> detailIdList = Lists.newArrayList();
    for(com.ewing.busi.order.dto.CommitOrdeReq.Item item : list){
      detailIdList.add(item.getDetailId());
      itemMap.put(item.getDetailId(), item.getItemCount());
    }
    List<OrderDetail> detailList = baseDao.findMuti(detailIdList, OrderDetail.class);
    
    List<Integer> orderInfoIdList = Lists.newArrayList();//一般是同一次提交只有一个orderinfo的
    for(OrderDetail detail : detailList){
      orderInfoIdList.add(detail.getOrderId());
    }
    List<OrderInfo> orderList =  baseDao.findMuti(detailIdList, OrderInfo.class);
    
    //更新操作
    for(OrderDetail detail : detailList){
      detail.setStatus(OrderStatus.WAIT_PAY.getValue());
      detail.setItemCount(itemMap.get(detail.getId()));
      
      baseDao.update(detail);
    }
    for(OrderInfo order : orderList){
      order.setStatus(OrderStatus.WAIT_PAY.getValue());
      order.setPayWay(req.getPayWayId());
      order.setPhone(null != address ? address.getPhone() : StringUtils.EMPTY);
      order.setProvince(null != address ? address.getProvince() : StringUtils.EMPTY);
      order.setCity(null != address ? address.getCity() : StringUtils.EMPTY);
      order.setRegion(null != address ? address.getRegion() : StringUtils.EMPTY);
      order.setAddress(null != address ? address.getAddress() : StringUtils.EMPTY);
      
      baseDao.update(order);
    }
    
    return true;
  }

  public LightOrderInfoResp submitRefund(Integer loginCusId, SubmitRefundReq req) {
    return null;
  }
}
