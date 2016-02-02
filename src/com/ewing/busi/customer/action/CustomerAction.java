package com.ewing.busi.customer.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.customer.dto.LightAddressInfoResp;
import com.ewing.busi.customer.model.Customer;
import com.ewing.busi.customer.service.CustomerService;
import com.ewing.core.app.action.base.BaseAction;

public class CustomerAction extends BaseAction {

    private static Logger logger = Logger.getLogger(CustomerAddressAction.class);

    @Resource
    private CustomerService customerService;

    /**
     * 根据id查找客户信息
     * 
     * @author Joeson
     */
    public void queryById() {
        try {
            LightAddressInfoReq req = getParamJson(LightAddressInfoReq.class);
            Integer cusId = req.getCusId();
            checkRequired(cusId, "cusId");

            Customer customer = customerService.queryById(cusId);
            outSucResult(customer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    }

    /**
     * 保存客户信息
     * 
     * @author Joeson
     */
    public void saveCustomer() {
        try {
            Customer customer = getParamJson(Customer.class);

            customerService.add(customer);
            outSucResult(customer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    }

    /**
     * 更新客户信息
     * 
     * @author Joeson
     */
    public void updateCustomer() {
        try {
            Customer customer = getParamJson(Customer.class);

            customerService.update(customer);
            outSucResult(customer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    }

}
