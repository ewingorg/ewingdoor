package com.ewing.core.app.action.base;

import com.ewing.common.constants.AjaxRespCode;

/**
 * @author tanson lin
 * 
 * @create:2012-2-23
 * @description:在action中提供不同返回信息的工具类。
 */
public class ResponseUtils {

    
    /**
     * 成功
     * 
     * @param retInfo
     * @return
     */
    public static ResponseData createByRespCode(AjaxRespCode respCode) {
        ResponseData responseData = new ResponseData();
        responseData.setSuccess(true);
        responseData.setRetCode(respCode.code.toString());
        responseData.setRetinfo(respCode.message);
        return responseData;
    }
    
	/**
	 * 成功
	 * 
	 * @param retInfo
	 * @return
	 */
	public static ResponseData success(String... retInfo) {
		ResponseData responseData = new ResponseData();
		responseData.setSuccess(true);
		responseData.setRetinfo(retInfo[0]);
		return responseData;
	}

	/**
	 * 失败
	 * 
	 * @param retInfo
	 * @return
	 */
	public static ResponseData fail(String... retInfo) {
		ResponseData responseData = new ResponseData();
		responseData.setSuccess(false);
		responseData.setRetinfo(retInfo[0]);
		return responseData;
	}
}
