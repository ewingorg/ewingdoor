package com.ewing.busi.customer.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.ewing.busi.customer.model.Customer;
import com.ewing.busi.customer.service.CustomerService;
import com.ewing.common.constants.AjaxRespCode;
import com.ewing.core.app.action.base.ActionException;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.action.base.ResponseData;
import com.ewing.core.app.action.base.ResponseUtils;
import com.ewing.core.app.bean.UserInfo;
import com.ewing.core.app.control.SessionControl;
import com.ewing.core.servlet.CaptchaServlet;

/**
 * 消费登陆
 * 
 * @author tansonlam
 * @createDate 2016年2月16日
 * 
 */
public class CustomerLoginAction extends BaseAction {
    private static Logger logger = Logger.getLogger(CustomerLoginAction.class);
    @Resource
    private CustomerService customerService;

    /**
     * 用户登陆
     * 
     * @throws ActionException
     */
    public void userLogin() throws ActionException {
        ResponseData responseData;
        try {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String checkCode = request.getParameter("checkCode");
            if (!CaptchaServlet.validate(request, checkCode)) {
                responseData = ResponseUtils.createByRespCode(AjaxRespCode.CUSTOMER_LOGIN_FAIL);
            } else {
                List<Customer> userList = customerService.findUser(userName);
                if (userList.isEmpty()) {
                    responseData = ResponseUtils.fail("登陆失败！不存在该用户名称");
                } else {
                    Customer customer = userList.get(0);
                    if (customer.getPassword().equals(password.trim())) {
                        responseData = ResponseUtils.success("登陆成功！");
                        UserInfo userInfo = new UserInfo();
                        BeanUtils.copyProperties(userInfo, customer);
                        SessionControl.setUserInfo(request, userInfo);
                        responseData = ResponseUtils
                                .createByRespCode(AjaxRespCode.CUSTOMER_LOGIN_SUC);
                    } else {
                        responseData = ResponseUtils
                                .createByRespCode(AjaxRespCode.CUSTOMER_LOGIN_FAIL);
                    }

                }
            }

        } catch (Exception e) {
            logger.error(e, e);
            responseData = ResponseUtils.createByRespCode(AjaxRespCode.CODE_UNKNOW);
        }
        this.outResult(responseData);
    }

    /**
     * 用户退出
     * 
     * @throws ActionException
     */
    public void userLogOut() throws ActionException {
        try {
            SessionControl.removeUserInfo(request);
            outSucResult(null);
        } catch (Exception e) {
            logger.error(e, e);
        }
    }
}
