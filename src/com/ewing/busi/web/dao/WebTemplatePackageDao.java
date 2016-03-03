package com.ewing.busi.web.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.web.model.WebTemplatePackage;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * 类/接口注释
 * 
 * @author tansonlam
 * @createDate 2016年3月3日
 * 
 */
@Repository("webTemplatePackageDao")
public class WebTemplatePackageDao {
    @Resource
    private BaseDao baseDao;

    /**
     * 查询模板包
     * 
     * @param templatePackageId
     * @return
     */
    public WebTemplatePackage getWebTemplatePackage(Integer templatePackageId) {
        StringBuilder query = new StringBuilder();
        query.append(" and id = ").append(templatePackageId);
        query.append(" and iseff = '").append(IsEff.EFFECTIVE.getValue()).append("'");
        return baseDao.findOne(query.toString(), WebTemplatePackage.class);

    }
}
