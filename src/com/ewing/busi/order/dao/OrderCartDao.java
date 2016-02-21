package com.ewing.busi.order.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.order.dto.LightOrderCartResp;
import com.ewing.busi.order.model.OrderCart;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.utils.PageUtil;

@Repository("orderCartDao")
public class OrderCartDao {

    @Resource
    private BaseDao baseDao;

    /**
     * 根据客户id查询 购物车列表
     * @param cusId
     * @author Joeson
     * @param pageSize 
     * @param page 
     * @param clazz 
     */
    public List<LightOrderCartResp> queryByCusId(Integer cusId, Integer page, Integer pageSize) {
        StringBuilder sql = new StringBuilder();
        sql.append("select oc.id,oc.resource_id,oc.item_count,wr.price,wr.name,wr.image_url from order_cart oc inner join web_resource wr on oc.resource_id = wr.id where 1=1 ");
        sql.append(" and oc.customer_id=").append(cusId);
        sql.append(" and oc.iseff = ").append(IsEff.EFFECTIVE.getValue());
        sql.append(" order by oc.last_update desc");
        sql.append(" limit ").append(PageUtil.getOffset(page, pageSize)).append(" , ").append(PageUtil.getLimit(page, pageSize));
        
        return baseDao.noMappedObjectQuery(sql.toString(), LightOrderCartResp.class);
    }

    public List<OrderCart> findByIdAndCusId(List<Integer> cartIdList, Integer cusId) {
        StringBuilder query = new StringBuilder();
        query.append(" id in (").append(StringUtils.join(cartIdList.toArray(), ",")).append(")");
        query.append(" and customer_id = ").append(cusId);
        return baseDao.find(query.toString(), OrderCart.class);
    }

}
