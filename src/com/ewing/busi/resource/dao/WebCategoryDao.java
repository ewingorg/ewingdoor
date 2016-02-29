package com.ewing.busi.resource.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.model.WebCategory;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;

@Repository
public class WebCategoryDao {

    @Resource
    private BaseDao baseDao;

    public List<WebCategory> findByUserId(Integer userId) {
        StringBuilder query = new StringBuilder();
        query.append(" level !='0' ");
        query.append(" and user_id = ").append(userId);
        query.append(" and iseff = '").append(IsEff.EFFECTIVE.getValue()).append("'");
        return baseDao.find(query.toString(), WebCategory.class);
    }

}
