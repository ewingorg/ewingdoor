package com.ewing.busi.customer.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.customer.aop.CustomerLoginFilter;
import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.customer.model.Customer;
import com.ewing.busi.customer.service.CustomerService;
import com.ewing.busi.resource.action.WebResourceAction;
import com.ewing.common.utils.SystemPropertyUtils;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.utils.IntegerUtils;

/**
 * 客户信息相关的操作
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月3日
 * 
 */
public class CustomerAction extends BaseAction {

    private static Logger logger = Logger.getLogger(CustomerAddressAction.class);

    @Resource
    private CustomerService customerService;

    /**
     * 根据id查找客户信息
     * 
     * @author Joeson
     */
    // @CustomerLoginFilter
    public void queryById() {
        try {
            LightAddressInfoReq req = getParamJson(LightAddressInfoReq.class);
            Integer cusId = req.getCusId();
            checkRequired(cusId, "cusId");
            isTrue(IntegerUtils.equals(cusId,
                    SystemPropertyUtils.CUSTOMER_LOGIN_VALIDATE ? getLoginUserId() : cusId), "非法操作");

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
    // @CustomerLoginFilter
    public void updateCustomer() {
        try {
            Customer customer = getParamJson(Customer.class);
            isTrue(IntegerUtils.equals(
                    customer.getId(),
                    SystemPropertyUtils.CUSTOMER_LOGIN_VALIDATE ? getLoginUserId() : customer
                            .getId()), "非法操作");
            customerService.update(customer);
            outSucResult(customer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    }

}
