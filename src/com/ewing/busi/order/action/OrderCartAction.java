package com.ewing.busi.order.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.customer.action.CustomerAddressAction;
import com.ewing.busi.customer.aop.CustomerLoginFilter;
import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.order.dto.LightOrderCartResp;
import com.ewing.busi.order.dto.SubmitCartReq;
import com.ewing.busi.order.service.OrderCartService;
import com.ewing.busi.resource.action.WebResourceAction;
import com.ewing.common.utils.SystemPropertyUtils;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.utils.IntegerUtils;

/**
 * 购物车的相关操作
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月16日
 */
public class OrderCartAction extends BaseAction{
    
    private static Logger logger = Logger.getLogger(WebResourceAction.class);
    @Resource
    private OrderCartService orderCartService;

    /**
     * 获取首页产品列表
     */
//    @CustomerLoginFilter
    public void queryIndexCart() {

        try {
            LightAddressInfoReq request = getParamJson(LightAddressInfoReq.class);
            Integer page = request.getPage();
            Integer pageSize = request.getPageSize();
            checkRequired(page, "page");
            checkRequired(pageSize, "pageSize");
            
            //@TODO 抽取dto对象
            List<LightOrderCartResp> list = orderCartService.queryByCusId(getLoginUserId(), page, pageSize);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("list", list);
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
//    @CustomerLoginFilter
    public void balanceCart(){
        try {
            SubmitCartReq req = getParamJson(SubmitCartReq.class);
            checkRequired(req, "req");
            
            //@TODO 抽取dto对象
            Integer orderId = orderCartService.balanceCart(req, getLoginUserId());
            outSucResult(orderId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    }
    
    

}