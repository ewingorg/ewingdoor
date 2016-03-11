package com.ewing.common.utils;

import org.apache.log4j.Logger;

import com.ewing.utils.PropertyUtil;

/**
 * 系统的一些参数的设置
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月13日
 */
public class SystemPropertyUtils {

    private static Logger logger = Logger.getLogger(SystemPropertyUtils.class);

    
    public final static boolean CUSTOMER_LOGIN_VALIDATE = Boolean.valueOf(PropertyUtil
            .getProperty("customer.validatelogin.open"));

    
    //资源库图片站点
    public final static String RESOURCE_WEBSTIE = PropertyUtil.getProperty("resource.website");

    //商店名称 @TODO后期撤换掉
    public final static String SHOP_NAME= PropertyUtil.getProperty("system.shop.name");
    
    /** 快递查询接口调用的appid**/
    public final static String EXPRESS_APPID = PropertyUtil.getProperty("express_appid");
    
    /** 商店站点 **/ 
    public final static String SHOP_DOMAIN= PropertyUtil.getProperty("shop.domain");
    
}
