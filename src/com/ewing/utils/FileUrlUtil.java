package com.ewing.utils;

import org.apache.axis.utils.StringUtils;

import com.ewing.common.utils.SystemPropertyUtils;

/**
 * 文件URL转换工具类
 * 
 * @author tansonlam
 * @createDate 2016年2月26日
 * 
 */
public class FileUrlUtil {

    /**
     * 转成资源相关的路径
     * 
     * @param relativePath
     * @return
     */
    public static String convertResourceUrl(String relativePath) {
        if (StringUtils.isEmpty(relativePath))
            return null;
        if (StringUtils.isEmpty(SystemPropertyUtils.RESOURCE_WEBSTIE))
            return relativePath;
        else
            return SystemPropertyUtils.RESOURCE_WEBSTIE + relativePath;
    }

    /**
     * 转换成商店相关的URL
     * 
     * @param relativePath
     * @return
     */
    public static String convertShopUrl(String relativePath) {
        if (StringUtils.isEmpty(relativePath))
            return null;
        if (StringUtils.isEmpty(SystemPropertyUtils.SHOP_DOMAIN))
            return relativePath;
        else
            return SystemPropertyUtils.SHOP_DOMAIN + relativePath;
    }
}
