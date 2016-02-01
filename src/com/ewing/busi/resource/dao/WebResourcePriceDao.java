package com.ewing.busi.resource.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.model.WebResourcePrice;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * 资源价格
 * 
 * @author tansonlam
 * @createDate 2016年1月27日
 * 
 */
@Repository("webResourcePriceDao")
public class WebResourcePriceDao {
    @Resource
    public BaseDao baseDao;

    /**
     * 查找指定资源的价格设置
     * 
     * @param resourceId
     * @return
     */
    public List<WebResourcePrice> findByResourceId(Integer resourceId) {
        StringBuffer querySql = new StringBuffer();
        querySql.append("iseff = ").append("'" + IsEff.EFFECTIVE.getValue() + "'");
        querySql.append(" and resource_id =").append(resourceId);
        querySql.append(" order by rank");
        return baseDao.find(querySql.toString(), WebResourcePrice.class);

    }
}
