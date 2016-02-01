package com.ewing.busi.resource.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.model.WebResource;
import com.ewing.common.constants.IsEff;
import com.ewing.common.constants.IsOnline;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.util.PageBean;

/**
 * 用户地址相关的操作
 * 
 * @author chenxuegui.cxg@alibaba-inc.com
 * @createDate 2016年1月29日
 */
@Repository("webResourceDao")
public class WebResourceDao {

    @Resource
    public BaseDao baseDao;

    /**
     * 获取资源信息，支持分页获取数据
     * 
     * @param userId 用户ID
     * @param isHot 是否热门资源
     * @param page
     * @param pageSize
     * 
     * @return
     */
    public List<WebResource> pageQueryHotResource(Integer userId, Integer isHot, Integer page,
            Integer pageSize) {
        StringBuffer querySql = new StringBuffer();
        querySql.append("iseff = ").append("'" + IsEff.EFFECTIVE.getValue() + "'");
        querySql.append(" and is_hot =").append(isHot);
        querySql.append(" and user_id=").append(userId);
        querySql.append(" and is_online=").append(IsOnline.ONLINE.getValue());
        querySql.append(" order by last_update");
        PageBean<WebResource> result = baseDao.pageQuery(querySql.toString(), pageSize, page,
                WebResource.class);
        return result.getResult();
    }

    /**
     * 查找单个资源信息
     * 
     * @param resourceId
     * @return
     */
    public WebResource findOne(Integer resourceId) {
        return baseDao.findOne(resourceId, WebResource.class);
    }
}
