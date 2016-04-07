package com.ewing.extranal.sms.service;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ewing.busi.customer.action.CustomerAddressAction;
import com.ewing.common.utils.SystemPropertyUtils;
import com.ewing.extranal.sms.dto.SmsResp;
import com.ewing.utils.JsonUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 短信服务api
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年4月7日
 */
public class SmsApiService {
  
  private static Logger logger = Logger.getLogger(SmsApiService.class);
  
  public static boolean sendAuthSms(String phone,String code) throws ApiException{
      String result = sendAuthSms(SystemPropertyUtils.SMS_SERVER_URL, SystemPropertyUtils.SMS_SERVER_APPKEY, SystemPropertyUtils.SMS_SERVER_SECRET, SystemPropertyUtils.SMS_FREE_SIGN_NAME, phone, code);
      
      logger.info("result : " + result);
      if(JsonUtils.isGoodJson(result)){
        SmsResp resp = JsonUtils.toObject(result, SmsResp.class);
        return null != resp && StringUtils.equals(resp.getErr_code(), "0");
      }
      
      return false;
  }
  
  private static String sendAuthSms(String url, String appKey, String secret,String freeSignName, String phone,String code) throws ApiException{
    TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
    AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
    req.setExtend("123456");
    req.setSmsType("normal");
    req.setSmsFreeSignName(freeSignName);
    req.setSmsParamString("{\"code\":\""+code+"\",\"product\":\"alidayu\"}");
    req.setRecNum(phone);
    req.setSmsTemplateCode("SMS_7261316");
    AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
    return rsp.getBody();
  }

}
