package com.ewing.common.constants;

import org.apache.commons.lang.StringUtils;

public enum AjaxRespCode {
    /**
     * 成功
     */
    CODE_SUC(2000000, "成功"),
    /**
     * 错误
     */
    CODE_ERROR(5000000, "出错"),

    /**
     * 未知错误
     */
    CODE_UNKNOW(5000001, "未知"),

    /**
     * 客户未登陆
     */
    CUSTOMER_NOLOGIN(6000001, "客户未登陆"),
    
    /**
     * 客户登陆成功
     */
    CUSTOMER_LOGIN_SUC(6000002, "客户登陆成功"),
    /**
     * 客户登陆失败
     */
    CUSTOMER_LOGIN_FAIL(6000003, "客户登陆失败");

    public Integer code;
    public String message;

    AjaxRespCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getCn(Integer value) {
        if (null == value) {
            return StringUtils.EMPTY;
        }
        for (AjaxRespCode type : AjaxRespCode.values()) {
            if (type.code == value.intValue()) {
                return type.message;
            }
        }
        return StringUtils.EMPTY;
    }

}
