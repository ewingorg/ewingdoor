package com.ewing.busi.express.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.express.dto.ExpressResp;
import com.ewing.core.express.service.ExpressApiService;

@Repository("expressService")
public class ExpressService {
  @Resource
  private ExpressApiService expressApiService;
  
  public static List<ExpressResp> list(String com,String num){
    if(StringUtils.isEmpty(com) || StringUtils.isEmpty(num)){
      return Collections.EMPTY_LIST;
    }
    
//    ExpressApiService.
    return null;
    
    
  }
    
    

}
