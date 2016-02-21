package com.ewing.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 处理float
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
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

}
