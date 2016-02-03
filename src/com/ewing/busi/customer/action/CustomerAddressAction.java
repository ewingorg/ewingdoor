package com.ewing.busi.customer.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.customer.dto.AddressDetailDto;
import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.customer.dto.LightAddressInfoResp;
import com.ewing.busi.customer.service.CustomerAddressService;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.util.StringUtil;

public class CustomerAddressAction extends BaseAction{
    
    private static Logger logger = Logger.getLogger(CustomerAddressAction.class);
    @Resource
    private CustomerAddressService customerAddressService;

    /**
     * 获取客户收货地址
     */
    public void queryIndexAddress() {

        try {
            LightAddressInfoReq req = getParamJson(LightAddressInfoReq.class);
            Integer cusId = req.getCusId();
            checkRequired(cusId, "cusId");

            List<LightAddressInfoResp> list = customerAddressService.queryByCusId(cusId);
            outSucResult(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常！");
        }
    }
    
    
    /**
     * 添加地址
     * 
     * @author Joeson
     */
    public void addAddress(){
        try {
            AddressDetailDto req = getParamJson(AddressDetailDto.class);
            checkRequired(req, "req");

            customerAddressService.add(req);
            outSucResult(StringUtil.EMPTY);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    

}
