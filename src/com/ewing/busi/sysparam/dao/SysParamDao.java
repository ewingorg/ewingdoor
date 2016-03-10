package com.ewing.busi.sysparam.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.sysparam.model.SysParam;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * seller操作类
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月20日
 *
 */
@Repository("sysParamDao")
public class SysParamDao {
    @Resource
    private BaseDao baseDao;
    
    public SysParam findByRootCodeAndParamName(String rootCode,String paramName){
      StringBuilder query = new StringBuilder();
      query.append("root_code = '").append(rootCode).append("'");
      query.append("and param_name = '").append(paramName).append("'");
      query.append("and iseff = '").append(IsEff.EFFECTIVE.getValue()).append("'");
      return baseDao.findOne(query.toString(), SysParam.class);
    }

    
    
}
