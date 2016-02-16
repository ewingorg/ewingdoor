package com.ewing.common.utils;

import org.apache.commons.lang.StringUtils;
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

    /**
     * 获取商店名称
     * 
     * @author Joeson
     */
    public static String getShopName() {
        String shopName = PropertyUtil.getProperty("system.shop.name");

        return StringUtils.isNotEmpty(shopName) ? shopName : StringUtils.EMPTY;
    }
    
    

}
