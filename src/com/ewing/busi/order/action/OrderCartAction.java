package com.ewing.busi.order.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.order.dto.AddCartReq;
import com.ewing.busi.order.dto.LightOrderCartResp;
import com.ewing.busi.order.dto.SubmitCartReq;
import com.ewing.busi.order.service.OrderCartService;
import com.ewing.common.constants.AjaxRespCode;
import com.ewing.core.app.action.base.BaseAction;

/**
 * 产品控制类，提供产品相关数据
 * 
 * @author tangzz@ucweb.com
 * @createDate 2016年1月25日
 */

public class OrderCartAction extends BaseAction {
  private static Logger logger = Logger.getLogger(OrderCartAction.class);
  @Resource
  private OrderCartService orderCartService;

  /**
   * 获取首页产品列表
   */
  // @CustomerLoginFilter
  public void queryIndexCart() {
    try {
      LightAddressInfoReq request = getParamJson(LightAddressInfoReq.class);
      Integer page = request.getPage();
      Integer pageSize = request.getPageSize();
      checkRequired(page, "page");
      checkRequired(pageSize, "pageSize");

      // @TODO 抽取dto对象
      List<LightOrderCartResp> list =
          orderCartService.queryByCusId(getLoginUserId(), page, pageSize);
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("list", list);
      outSucResult(map);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }

  /**
   * 提交购物车
   * 
   * @author Joeson
   */
  // @CustomerLoginFilter
  public void balanceCart() {
    try {
      SubmitCartReq req = getParamJson(SubmitCartReq.class);
      checkRequired(req, "req");

      // @TODO 抽取dto对象
      Integer orderId = orderCartService.balanceCart(req, getLoginUserId());
      outSucResult(orderId);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }


  /**
   * 添加购物车
   * 
   * @author Joeson
   */
  public void addCart() {
    try {
      AddCartReq req = getParamJson(AddCartReq.class);
      Integer resourceId = req.getResourceId();
      Integer count = req.getCount();
      checkRequired(resourceId, "resourceId");
      checkRequired(count, "count");

      orderCartService.addCart(getLoginUserId(), resourceId, count);
      outSucResult(AjaxRespCode.CODE_SUC);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }
}
