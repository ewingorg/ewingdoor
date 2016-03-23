package com.ewing.common.utils;

import org.apache.log4j.Logger;

import com.ewing.utils.PropertyUtils;

/**
 * 系统的一些参数的设置
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月13日
 */
public class SystemPropertyUtils {

    private static Logger logger = Logger.getLogger(SystemPropertyUtils.class);

    
    public final static boolean CUSTOMER_LOGIN_VALIDATE = Boolean.valueOf(PropertyUtils
            .getProperty("customer.validatelogin.open"));

    
    //资源库图片站点
    public final static String RESOURCE_WEBSTIE = PropertyUtils.getProperty("resource.website");

    //商店名称 @TODO后期撤换掉
    public final static String SHOP_NAME= PropertyUtils.getProperty("system.shop.name");
    
    /** 快递查询接口调用的appid**/
    public final static String EXPRESS_APPID = PropertyUtils.getProperty("express_appid");
    
    /** 商店站点 **/ 
    public final static String SHOP_DOMAIN= PropertyUtils.getProperty("shop.domain");
    
    /** 是否需要验证登陆状态 **/
    public static boolean isAuth(){
      return CUSTOMER_LOGIN_VALIDATE;
    }
    
    /**
     * 是否是开发模式<br/>
     * 验证是否需要登陆验证，如果不需要登陆验证，就认为是开发模式；如果不是开发模式，就是生产模式
     * @author Joeson
     */
    public static boolean isDev(){
      return CUSTOMER_LOGIN_VALIDATE != true;
    }
}
