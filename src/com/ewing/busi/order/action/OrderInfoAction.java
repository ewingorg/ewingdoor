package com.ewing.busi.order.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.customer.aop.CustomerLoginFilter;
import com.ewing.busi.order.constants.PayWay;
import com.ewing.busi.order.dto.ConfirmOrderReq;
import com.ewing.busi.order.dto.ConfirmOrderReq.Item;
import com.ewing.busi.order.dto.LightOrderInfoReq;
import com.ewing.busi.order.dto.LightOrderInfoResp;
import com.ewing.busi.order.dto.OrderInfoDetailReq;
import com.ewing.busi.order.dto.OrderInfoDetailResp;
import com.ewing.busi.order.service.OrderCartService;
import com.ewing.busi.order.service.OrderInfoService;
import com.ewing.busi.resource.action.WebResourceAction;
import com.ewing.common.constants.ResponseCode;
import com.ewing.common.utils.SystemPropertyUtils;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.utils.IntegerUtils;
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
    private OrderInfoService orderInfoService;

    /**
     * 获取首页产品列表
     */
    @CustomerLoginFilter
    public void queryIndexOrder() {

        try {
            LightOrderInfoReq request = getParamJson(LightOrderInfoReq.class);
            Integer cusId = request.getCusId();
            isTrue(IntegerUtils.equals(cusId, getLoginUserId()), "非法操作");
            Character status = request.getStatus();
            Integer page = request.getPage();
            Integer pageSize = request.getPageSize();

            checkRequired(cusId, "cusId");

            // @TODO 抽取dto对象
            List<LightOrderInfoResp> list = orderInfoService.queryByCusId(cusId, status, page,
                    pageSize);
            Map<String, Object> map = Maps.newHashMap();
            map.put("list", list);
            map.put("shopName", SystemPropertyUtils.getShopName());
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

            // @TODO 抽取dto对象
            List<OrderInfoDetailResp> list = orderInfoService.getByIdAndCusId(orderId, getLoginUserId());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", list);
            map.put("payWay", PayWay.values());
            map.put("shopName", SystemPropertyUtils.getShopName());
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
    public void closeOrder(){
        try {
            OrderInfoDetailReq req = getParamJson(OrderInfoDetailReq.class);
            Integer orderId = req.getOrderId();
            checkRequired(orderId, "orderId");

            orderInfoService.cancelOrder(orderId);
            outSucResult(ResponseCode.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    }

    /**
     * 支付
     * 
     * @author Joeson
     */
    public void payOrder(){
        
    }
}
