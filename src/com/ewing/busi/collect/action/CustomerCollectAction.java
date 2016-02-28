package com.ewing.busi.collect.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.collect.dto.AddCollectReq;
import com.ewing.busi.collect.dto.LightCollectReq;
import com.ewing.busi.collect.dto.LightCollectResp;
import com.ewing.busi.collect.service.CustomerCollectService;
import com.ewing.busi.customer.model.Customer;
import com.ewing.busi.order.dto.LightOrderCartResp;
import com.ewing.busi.resource.action.WebResourceAction;
import com.ewing.common.constants.AjaxRespCode;
import com.ewing.core.app.action.base.BaseAction;

/**
 * 
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年2月23日
 */
public class CustomerCollectAction extends BaseAction{
    
    private static Logger logger = Logger.getLogger(CustomerCollectAction.class);

    @Resource
    private CustomerCollectService customerCollectService;
    
    /**
     * 查找信息
     * 
     * @author Joeson
     */
    public void queryIndex() {
        try {
            LightCollectReq req = getParamJson(LightCollectReq.class);
            checkRequired(req.getPage(), "page");
            checkRequired(req.getPageSize(), "pageSize");
            
            List<LightCollectResp> list = customerCollectService.findByCusId(getLoginCusId(), req.getPage(), req.getPageSize());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", list);
            outSucResult(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    } 
    
    /**
     * 添加收件夹
     * 
     * @author Joeson
     */
    public void addCollect(){
        try {
            AddCollectReq req = getParamJson(AddCollectReq.class);
            checkRequired(req.getResId(), "resource_id");
            
            customerCollectService.addCollect(getLoginCusId(), req.getResId());
            outSucResult(AjaxRespCode.CODE_SUC);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    }

}
