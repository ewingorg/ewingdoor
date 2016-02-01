package com.ewing.busi.resource.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.model.WebResourceSpec;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;

@Repository("webResourceSpecDao")
public class WebResourceSpecDao {
    @Resource
    public BaseDao baseDao;

    /**
     * 获取配置的资源規格
     * 
     * @param resourceId
     * @return
     */
    public List<WebResourceSpec> getConfigureSpecs(Integer resourceId) {

        StringBuffer querySql = new StringBuffer();
        querySql.append("iseff = ").append("'" + IsEff.EFFECTIVE.getValue() + "'");
        querySql.append(" and resource_id =").append(resourceId);
        querySql.append(" order by rank");
        return baseDao.find(querySql.toString(), WebResourceSpec.class);
    }

}
