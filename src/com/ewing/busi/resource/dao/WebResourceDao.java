package com.ewing.busi.resource.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.dto.WebResourceDto;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.common.constants.IsEff;
import com.ewing.common.constants.IsOnline;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.util.PageBean;
import com.ewing.utils.PageUtil;

/**
 * 用户地址相关的操作
 * 
 * @author Joeson<chenxuegui1234@163.com>
 * @createDate 2016年1月29日
 */
@Repository("webResourceDao")
public class WebResourceDao {

  @Resource
  public BaseDao baseDao;

  /**
   * 获取资源信息，支持分页获取数据
   * 
   * @param shopId 商铺ID
   * @param isHot 是否热门资源
   * @param page
   * @param pageSize
   * 
   * @return
   */
  public List<WebResource> pageQueryHotResource(Integer shopId, Integer isHot, Integer page,
      Integer pageSize) {
    StringBuffer querySql = new StringBuffer();
    querySql.append("iseff = ").append("'" + IsEff.EFFECTIVE.getValue() + "'");
    querySql.append(" and is_hot =").append(isHot);
    querySql.append(" and shop_id=").append(shopId);
    querySql.append(" and is_online=").append(IsOnline.ONLINE.getValue());
    querySql.append(" and iseff = '").append(IsEff.EFFECTIVE.getValue()).append("'");
    querySql.append(" order by last_update");
    PageBean<WebResource> result =
        baseDao.pageQuery(querySql.toString(), pageSize, page, WebResource.class);
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

  public List<WebResourceDto> queryByCategory(Integer shopId, Integer categoryId, Integer page,
      Integer pageSize) {
    StringBuilder sql =
        new StringBuilder("select wr.* from web_resource wr"
            + " inner join web_category wc on wr.category_id = wc.id" + " where 1= 1");
    sql.append(" and wc.shop_id = ").append(shopId);
    sql.append(" and wc.shop_id = ").append(shopId);
    sql.append(" and wc.id = ").append(categoryId);
    sql.append(" and wc.iseff = '").append(IsEff.EFFECTIVE.getValue()).append("'");
    sql.append(" and wr.iseff = '").append(IsEff.EFFECTIVE.getValue()).append("'");
    sql.append(" order by wr.last_update desc");
    
    PageBean<WebResourceDto> pageBean = baseDao.noMappedObjectPageQuery(sql.toString(), WebResourceDto.class, page, pageSize);
    return pageBean.getResult();
  }
}
