package com.ewing.busi.order.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.model.OrderDetail;
import com.ewing.core.jdbc.BaseDao;

@Repository("orderDetailDao")
public class OrderDetailDao {
    
    @Resource
    private BaseDao baseDao;

    public List<OrderDetail> findByOrderIdAndCusId(Integer orderId, Integer cusId) {
        StringBuilder query = new StringBuilder();
        query.append(" order_id = ").append(orderId);
        query.append(" and customer_id = ").append(cusId);
        query.append(" order by last_update desc");
        
        return baseDao.find(query.toString(), OrderDetail.class);
    }
    
    
    

}
