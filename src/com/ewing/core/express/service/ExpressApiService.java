package com.ewing.core.express.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.core.express.vo.ExpressReqDto;
import com.ewing.utils.HttpUtils;

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
    private final String REQUEST_URL = "http://www.kuaidihelp.com/api?id=%s&com=%s&num=%s&show=%s";

    public String request(ExpressReqDto dto) {
        return request(dto.getId(), dto.getCom(), dto.getNum());
    }
    
    public String request(String id,String com,String num){
        String url = String.format(REQUEST_URL, id, com, num); 

        String response = HttpUtils.request(url, "GET", null, null, "utf8");
        return response;
    }

}
