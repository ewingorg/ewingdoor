package com.ewing.busi.order.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.busi.order.service.OrderCartService;
import com.ewing.busi.order.service.OrderInfoService;
import com.ewing.busi.resource.action.WebResourceAction;
import com.ewing.core.app.action.base.BaseAction;

/**
 * 订单的相关操作
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年2月2日
 *
 */
public class OrderInfoAction extends BaseAction{
    
    private static Logger logger = Logger.getLogger(WebResourceAction.class);
    @Resource
    private OrderCartService orderCartService;
    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 获取首页产品列表
     */
    public void queryIndexOrder() {

        try {
            LightAddressInfoReq request = getParamJson(LightAddressInfoReq.class);
            Integer cusId = request.getCusId();
            checkRequired(cusId, "cusId");
            
            //@TODO 抽取dto对象
            List<OrderInfo> list = orderInfoService.queryByCusId(cusId);
            outSucResult(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    }
    
    

}
