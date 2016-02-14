package com.ewing.busi.order.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.order.dto.SubmitCartReq;
import com.ewing.busi.order.service.OrderCartService;
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
            Integer page = request.getPage();
            Integer pageSize = request.getPageSize();
            checkRequired(cusId, "cusId");
            checkRequired(page, "page");
            checkRequired(pageSize, "pageSize");
            
            //@TODO 抽取dto对象
            Object[] objs = orderCartService.queryByCusId(cusId, page, pageSize);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("list", objs[0]);
            map.put("totalPrice", objs[1]);
            outSucResult(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    }
    
    /**
     * 提交gou
     * 
     * @author Joeson
     */
    public void balanceCart(){
        try {
            SubmitCartReq req = getParamJson(SubmitCartReq.class);
            checkRequired(req, "req");
            
            //@TODO 抽取dto对象
            Integer orderId = orderCartService.balanceCart(req);
            outSucResult(orderId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
        
    }
    
    

}