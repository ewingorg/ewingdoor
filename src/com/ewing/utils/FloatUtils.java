package com.ewing.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 处理float
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月18日
 *
 */
public class FloatUtils {

    public static float parseFloat(String value) {
        if (StringUtil.isEmpty(value)) {
            return 0f;
        }

        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            return 0f;
        }
    }
    
    public static float toFloat(Float value){
      return null == value ? 0f : value;
    }

}
