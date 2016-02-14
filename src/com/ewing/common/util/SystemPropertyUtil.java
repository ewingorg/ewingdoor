package com.ewing.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ewing.util.PropertyUtil;

/**
 * 系统的一些参数的设置
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年2月13日
 */
public class SystemPropertyUtil {

    private static Logger logger = Logger.getLogger(SystemPropertyUtil.class);

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
