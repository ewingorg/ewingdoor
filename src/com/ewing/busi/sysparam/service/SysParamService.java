package com.ewing.busi.sysparam.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.sysparam.dao.SysParamDao;
import com.ewing.busi.sysparam.model.SysParam;

@Repository("sysParamService")
public class SysParamService {

  @Resource
  private SysParamDao sysParamDao;
  public SysParam findByRootCodeAndParamName(String rootCode,String paramName){
    if(StringUtils.isEmpty(rootCode) || StringUtils.isEmpty(paramName)){
      return null;
    }
    
    return sysParamDao.findByRootCodeAndParamName(rootCode, paramName);
  }
}
