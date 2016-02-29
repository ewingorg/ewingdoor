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
     * 将相对路径的图片转成绝对路径
     * 
     * @param relativePath
     * @return
     */
    public static String convertImgUrl(String relativePath) {
        if (StringUtils.isEmpty(relativePath))
            return null;
        if (StringUtils.isEmpty(SystemPropertyUtils.RESOURCE_WEBSTIE))
            return relativePath;
        else
            return SystemPropertyUtils.RESOURCE_WEBSTIE + relativePath;
    }
}
