package com.ewing.core.json.config;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 通用布尔值转换（1:true,0:false）
 *  
 */
public class BooleanProcessor implements JsonValueProcessor {
	public Object processArrayValue(Object paramObject,
			JsonConfig paramJsonConfig) {

		boolean[] obj = {};
		if (paramObject instanceof Integer[]) {
			Integer[] dates = (Integer[]) paramObject;
			obj = new boolean[dates.length];
			for (int i = 0; i < dates.length; i++) {
				obj[i] = Boolean.valueOf(dates[i].intValue() == 1);
			}
		}
		return obj;
	}

	public Object processObjectValue(String paramString, Object paramObject,
			JsonConfig paramJsonConfig) {
		if (paramObject instanceof Integer) {
			return ((Integer) paramObject).intValue() == 1;
		}

		return false;
	}

}
