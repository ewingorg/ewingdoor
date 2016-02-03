package com.ewing.busi.order.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.order.model.OrderCart;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.busi.order.service.OrderCartService;
import com.ewing.busi.order.service.OrderInfoService;
import com.ewing.busi.resource.action.WebResourceAction;
import com.ewing.core.app.action.base.BaseAction;

public class OrderCartAction extends BaseAction{
    
    private static Logger logger = Logger.getLogger(WebResourceAction.class);
    @Resource
    private OrderCartService orderCartService;

    /**
     * 获取首页产品列表
     */
    public void queryIndexCart() {

        try {
            LightAddressInfoReq request = getParamJson(LightAddressInfoReq.class);
            Integer cusId = request.getCusId();
            checkRequired(cusId, "cusId");
            
            //@TODO 抽取dto对象
            List<OrderCart> list = orderCartService.queryByCusId(cusId);
            outSucResult(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    }
    
    

}