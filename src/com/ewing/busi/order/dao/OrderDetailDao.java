package com.ewing.busi.order.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.dto.OrderInfoDetailResp;
import com.ewing.busi.order.model.OrderDetail;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;

@Repository("orderDetailDao")
public class OrderDetailDao {

    @Resource
    private BaseDao baseDao;

    public List<OrderInfoDetailResp> findByOrderIdAndCusId(Integer orderId, Integer cusId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select od.id,od.order_id,od.resource_id,wr.name,wr.image_url,od.item_count,od.unit_price,od.cargo_price,od.total_price,od.status from order_detail od "
            + "inner join web_resource wr on od.resource_id = wr.id"
            + " where 1=1");
        sql.append(" and od.order_id = ").append(orderId);
        sql.append(" and od.customer_id = ").append(cusId);
        sql.append(" and od.iseff = '").append(IsEff.EFFECTIVE.getValue()).append("'");
        sql.append(" order by od.last_update desc"); 

        return baseDao.noMappedObjectQuery(sql.toString(), OrderInfoDetailResp.class);
    }

}
