package com.ewing.busi.web.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.web.model.WebTemplate;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * 
 * 资源模板DAO
 * 
 * @author tansonlam
 * @createDate 2016年3月3日
 * 
 */
@Repository("webTemplateDao")
public class WebTemplateDao {

    @Resource
    private BaseDao baseDao;

    /**
     * 查找模板
     * 
     * @param templateName 模板页面名称
     * @param templatePackageId 模板包ID
     * @return
     */
    public WebTemplate findByTemplateName(String templateName, Integer templatePackageId) {
        StringBuilder query = new StringBuilder();
        query.append(" and template_package_id = ").append(templatePackageId);
        query.append(" and template_path = '").append(templateName).append("'");
        query.append(" and iseff = '").append(IsEff.EFFECTIVE.getValue()).append("'");
        return baseDao.findOne(query.toString(), WebTemplate.class);
    }

}
