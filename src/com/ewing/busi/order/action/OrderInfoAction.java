package com.ewing.busi.order.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.customer.aop.CustomerLoginFilter;
import com.ewing.busi.customer.dao.CustomerAddressDao;
import com.ewing.busi.customer.model.CustomerAddress;
import com.ewing.busi.customer.service.CustomerAddressService;
import com.ewing.busi.order.constants.PayWay;
import com.ewing.busi.order.dto.AddOrdeReq;
import com.ewing.busi.order.dto.CommitOrdeReq;
import com.ewing.busi.order.dto.ConfirmOrderReq;
import com.ewing.busi.order.dto.ConfirmOrderReq.Item;
import com.ewing.busi.order.dto.LightOrderInfoReq;
import com.ewing.busi.order.dto.LightOrderInfoResp;
import com.ewing.busi.order.dto.OrderInfoDetailReq;
import com.ewing.busi.order.dto.OrderInfoDetailResp;
import com.ewing.busi.order.service.OrderCartService;
import com.ewing.busi.order.service.OrderDetailService;
import com.ewing.busi.order.service.OrderInfoService;
import com.ewing.busi.resource.action.WebResourceAction;
import com.ewing.busi.resource.helper.PayWayHelper;
import com.ewing.common.constants.AjaxRespCode;
import com.ewing.common.constants.ResponseCode;
import com.ewing.common.utils.SystemPropertyUtils;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.utils.IntegerUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 订单的相关操作
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月2日
 */
public class OrderInfoAction extends BaseAction {

  private static Logger logger = Logger.getLogger(WebResourceAction.class);
  @Resource
  private OrderCartService orderCartService;
  @Resource
  private OrderDetailService orderDetailService; 
  @Resource
  private OrderInfoService orderInfoService;
  @Resource
  private CustomerAddressService customerAddressService;
  @Resource
  private CustomerAddressDao customerAddressDao;


  /**
   * 获取首页产品列表
   */
  // @CustomerLoginFilter
  public void queryIndexOrder() {
    try {
      LightOrderInfoReq request = getParamJson(LightOrderInfoReq.class);
      Integer page = request.getPage();
      Integer pageSize = request.getPageSize();

      List<LightOrderInfoResp> list =
          orderInfoService.queryByCusId(getLoginCusId(), request.convertStatus(), page, pageSize);
      Map<String, Object> map = Maps.newHashMap();
      map.put("list", list);
      map.put("payWays", PayWayHelper.list());
      map.put("shopName", SystemPropertyUtils.SHOP_NAME);
      outSucResult(map);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }

  /**
   * 根据id查找detail
   * 
   * @author Joeson
   */
  public void getById() {
    try {
      OrderInfoDetailReq req = getParamJson(OrderInfoDetailReq.class);
      Integer orderId = req.getOrderId();
      checkRequired(orderId, "orderId");

      List<OrderInfoDetailResp> list = orderDetailService.findByOrderIdAndCusId(orderId, getLoginCusId());
      CustomerAddress defaultAddr = customerAddressService.findDefaultAddress(getLoginCusId());
      List<CustomerAddress> addrList = customerAddressService.queryByCusId(getLoginCusId());
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("list", list);
      map.put("addrList", addrList);
      map.put("payWays", PayWayHelper.list());
      map.put("defaultAddr", defaultAddr);
      outSucResult(map);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }

  /**
   * 确认订单
   * 
   * @author Joeson
   */
  public void confirmOrder() {
    try {
      ConfirmOrderReq req = getParamJson(ConfirmOrderReq.class);
      List<Item> list = new ArrayList<Item>();
      checkRequired(list, "list");

      orderInfoService.confirmOrder(req);
      // @TODO 抽取dto对象
      // List<OrderInfoDetailResp> list = orderInfoService.getById(orderId);
      // Map<String, Object> map = new HashMap<String, Object>();
      // map.put("list", list);
      // map.put("payWay", PayWay.values());
      // map.put("shopName", SystemPropertyUtil.getShopName());
      // outSucResult(map);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }

  /**
   * 关闭订单
   * 
   * @author Joeson
   */
  public void closeOrder() {
    try {
      OrderInfoDetailReq req = getParamJson(OrderInfoDetailReq.class);
      Integer orderId = req.getOrderId();
      checkRequired(orderId, "orderId");

      orderInfoService.closeOrder(getLoginCusId() ,orderId);
      outSucResult(ResponseCode.OK);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }

  public void addOrder() {
    try {
      AddOrdeReq req = getParamJson(AddOrdeReq.class);
      checkRequired(req, "入参不能为空");

      Integer orderId = orderInfoService.addOrder(getLoginCusId(), req);
      Map<String, Object> map = Maps.newHashMap();
      map.put("orderId", orderId);
      outSucResult(map);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }

  /**
   * 提交订单
   * 
   * @author Joeson
   */
  public void commitOrder() {
    try {
      CommitOrdeReq req = getParamJson(CommitOrdeReq.class);
      checkRequired(req, "入参不能为空");

      boolean result = orderInfoService.commitOrder(getLoginCusId(), req);
      if(result){
        outSucResult(AjaxRespCode.CODE_SUC.code);
      }else{
        outFailResult(AjaxRespCode.CODE_ERROR);;
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }
}
