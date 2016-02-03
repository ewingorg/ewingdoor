package com.ewing.busi.order.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.model.OrderInfo;
import com.ewing.core.jdbc.BaseDao;

@Repository("orderInfoDao")
public class OrderInfoDao {

    @Resource
    private BaseDao baseDao;

    /**
     * 根据客户id查询 订单列表
     * @param cusId
     * @author Joeson
     */
    public List<OrderInfo> queryByCusId(Integer cusId) {
        StringBuilder query = new StringBuilder();
        query.append("customer_id=").append(cusId);
        query.append(" order by last_update desc");
        
        return baseDao.find(query.toString(), OrderInfo.class);
    }

}
