package com.ewing.busi.customer.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.ewing.common.constants.AjaxRespCode;
import com.ewing.common.utils.SystemPropertyUtils;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.utils.PropertyUtils;

/**
 * 
 * 
 * @author tansonlam
 * @createDate 2016年2月16日
 * 
 */
@Aspect
public class CustomerValidateLoginAdvice {

    @Around("execution(* com.ewing.busi.*.action.*.*(..))  && @annotation(filter)")
    public Object aroundMethod(ProceedingJoinPoint pjd, CustomerLoginFilter filter)
            throws Throwable {
        if (!SystemPropertyUtils.CUSTOMER_LOGIN_VALIDATE
                || !(pjd.getTarget() instanceof BaseAction) || filter == null)
            return pjd.proceed();

        Method getLoginUserIdMethod = pjd.getTarget().getClass().getMethod("getLoginUserId");

        try {
            Integer userId = (Integer) getLoginUserIdMethod.invoke(pjd.getTarget());
        } catch (Exception e) {
            Method outFailResultMethod = pjd.getTarget().getClass()
                    .getMethod("outFailResult", AjaxRespCode.class);
            outFailResultMethod.invoke(pjd.getTarget(), AjaxRespCode.CUSTOMER_NOLOGIN);
        }

        return pjd.proceed();
    }
}
