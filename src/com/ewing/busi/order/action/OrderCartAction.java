package com.ewing.busi.order.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.order.dto.AddCartReq;
import com.ewing.busi.order.dto.LightOrderCartReq;
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
      LightOrderCartReq req = getParamJson(LightOrderCartReq.class);
      
      List<LightOrderCartResp> list =
          orderCartService.queryByCusId(getLoginCusId(), req);
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
      Integer orderId = orderCartService.balanceCart(getLoginCusId(), req);
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
      checkRequired(req, "入参不能为空");

      orderCartService.addCart(getLoginCusId(), req);
      outSucResult(AjaxRespCode.CODE_SUC);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }
}
