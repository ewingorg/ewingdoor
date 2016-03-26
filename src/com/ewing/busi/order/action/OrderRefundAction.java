package com.ewing.busi.order.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.order.dto.ApplyRefundtReq;
import com.ewing.busi.order.dto.LightOrderInfoResp;
import com.ewing.busi.order.dto.QueryRefundReq;
import com.ewing.busi.order.dto.SubmitRefundExpressReq;
import com.ewing.busi.order.dto.SubmitRefundReq;
import com.ewing.busi.order.service.OrderInfoService;
import com.ewing.busi.order.service.OrderRefundService;
import com.ewing.busi.resource.helper.PayWayHelper;
import com.ewing.common.constants.AjaxRespCode;
import com.ewing.common.utils.SystemPropertyUtils;
import com.ewing.core.app.action.base.BaseAction;
import com.google.common.collect.Maps;

/**
 * 订单退货的相关操作
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月2日
 */
public class OrderRefundAction extends BaseAction {
  private static Logger logger = Logger.getLogger(OrderRefundAction.class);
  
  @Resource
  private OrderRefundService orderRefundService;
  @Resource
  private OrderInfoService orderInfoService;
  
  /**
   * 客户申请退款，进入退款流程
   * 
   * @author Joeson
   */
  public void applyRefundOrder(){
    try {
      ApplyRefundtReq req = getParamJson(ApplyRefundtReq.class);
      checkRequired(req, "入参不能为空");
      checkRequired(req.getOrderId(), "orderId不能为空");

      LightOrderInfoResp resp = orderInfoService.queryByCusId(getLoginCusId(), req.getOrderId());
      Map<String, Object> map = Maps.newHashMap();
      map.put("order", resp);
      map.put("payWays", PayWayHelper.list());
      map.put("shopName", SystemPropertyUtils.SHOP_NAME);
      outSucResult(map);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }
  
  /**
   * 客户提交退款信息，需要提供退款货品的相关信息，等待商家进行确认
   * 
   * @author Joeson
   */
  public void submitRefundOrder(){
    try {
      SubmitRefundReq req = getParamJson(SubmitRefundReq.class);
      checkRequired(req, "入参不能为空");

      LightOrderInfoResp resp = orderRefundService.submitRefund(getLoginCusId(), req);
      Map<String, Object> map = Maps.newHashMap();
      map.put("order", resp);
      map.put("payWays", PayWayHelper.list());
      map.put("shopName", SystemPropertyUtils.SHOP_NAME);
      outSucResult(map);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }
  
  /**
   * 退款记录查看
   * 
   * @author Joeson
   */
  public void queryRefund(){
    try {
      QueryRefundReq req = getParamJson(QueryRefundReq.class);
      checkRequired(req, "入参不能为空");

//      RefundInfoResp resp = orderRefundService.queryRefund(getLoginCusId(), req);
//      Map<String, Object> map = Maps.newHashMap();
//      map.put("order", resp);
//      map.put("payWays", PayWayHelper.list());
//      map.put("shopName", SystemPropertyUtils.SHOP_NAME);
//      outSucResult(map);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }
  
  /**
   * 提交物流信息
   * 
   * @author Joeson
   */
  public void submitExpress(){
    try {
      SubmitRefundExpressReq req = getParamJson(SubmitRefundExpressReq.class);
      checkRequired(req, "入参不能为空");

      boolean result = orderRefundService.submitRefundExpress(getLoginCusId(), req);
      if(result){
        outSucResult(AjaxRespCode.CODE_SUC.code);
      }else{
        outFailResult("提交失败");
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }
  
  
}
