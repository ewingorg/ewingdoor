package com.ewing.busi.collect.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import com.ewing.busi.collect.dto.LightCollectResp;
import com.ewing.busi.collect.model.CustomerCollect;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.util.PageBean;

/**
 * 
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年2月23日
 */
@Repository("customerCollectDao")
public class CustomerCollectDao {
    
    @Resource
    private BaseDao baseDao;

    /**
     * 
     * @param cusId
     * @author Joeson
     */
    public List<LightCollectResp> findByCusId(Integer cusId, Integer page,Integer pageSize){
        Validate.notNull(cusId, "客户id不能为空");
        
        StringBuilder sql = new StringBuilder("select cc.id,cc.resource_id,wr.image_url,wr.price,wr.image_url,wr.stock_num,wr.name from customer_collect cc inner join web_resource wr on cc.resource_id = wr.id where 1=1 ");
        sql.append(" and customer_id=").append(cusId);
        
        PageBean<LightCollectResp> pageBean = baseDao.noMappedObjectPageQuery(sql.toString(), LightCollectResp.class, page, pageSize);
        return pageBean.getResult();
    }

    public CustomerCollect findByCusIdAndResId(Integer cusId,Integer resId){
        Validate.notNull(cusId, "客户id不能为空");
        Validate.notNull(resId, "资源id不能为空");
        
        StringBuilder query = new StringBuilder();
        query.append(" customer_id = ").append(cusId);
        query.append(" and resource_id = ").append(resId);
        query.append(" and iseff = '").append(IsEff.EFFECTIVE.getValue()).append("'");
        
        return baseDao.findOne(query.toString(), CustomerCollect.class);
    }
}
