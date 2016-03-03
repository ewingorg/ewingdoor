package com.ewing.core.express.service;

import org.springframework.stereotype.Repository;

import com.ewing.core.express.vo.ExpressReqDto;
import com.ewing.core.express.vo.ExpressRespDto;
import com.ewing.utils.HttpUtils;
import com.ewing.utils.JsonUtils;

/***
 * 快递请求service
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月21日
 */
@Repository("expressApiService")
public class ExpressApiService {

    /**
     * 请求url
     */
    private final String REQUEST_URL = "http://api.kuaidi.com/openapi.html?id=%s&com=%s&nu=%s";

    /**
     * 快递查询接口
     * @param id 快递查询接口appid
     * @param com 公司代号
     * @param num 快递单号
     * @author Joeson
     */
    public ExpressRespDto request(String id,String com,String num){
      String url = String.format(REQUEST_URL, id, com, num); 
      String response = HttpUtils.request(url, "GET", null, null, "utf8");
      return JsonUtils.toObject(response, ExpressRespDto.class);
    }

}
