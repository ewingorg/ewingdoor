package com.ewing.busi.express.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.express.dto.ExpressResp;
import com.ewing.core.express.service.ExpressApiService;
import com.ewing.core.express.vo.ExpressRespDto;

@Repository("expressService")
public class ExpressService {
  @Resource
  private ExpressApiService expressApiService;
  
  public ExpressRespDto query4Order(String com,String num){
    if(StringUtils.isEmpty(com) || StringUtils.isEmpty(num)){
      return null;
    }
    
    return expressApiService.request(com, num);
  }
    
    

}
