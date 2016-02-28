package com.ewing.common.utils;

import org.apache.log4j.Logger;

import com.ewing.utils.PropertyUtil;

/**
 * 系统的一些参数的设置
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年2月13日
 */
public class SystemPropertyUtils {

    private static Logger logger = Logger.getLogger(SystemPropertyUtils.class);

    public final static boolean CUSTOMER_LOGIN_VALIDATE = Boolean.valueOf(PropertyUtil
            .getProperty("customer.validatelogin.open"));

    public final static String RESOURCE_WEBSTIE = PropertyUtil.getProperty("resource.website");

    public final static String SHOP_NAME= PropertyUtil.getProperty("system.shop.name");
}
