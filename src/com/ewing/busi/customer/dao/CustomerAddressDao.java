package com.ewing.busi.customer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.customer.model.CustomerAddress;
import com.ewing.busi.order.model.OrderCart;
import com.ewing.core.jdbc.BaseDao;

@Repository("customerAddressDao")
public class CustomerAddressDao {

    @Resource
    private BaseDao baseDao;

    /**
     * 根据客户id查询 购物车列表
     * @param cusId
     * @author Joeson
     */
    public List<CustomerAddress> queryByCusId(Integer cusId) {
        StringBuilder query = new StringBuilder();
        query.append("customer_id=").append(cusId);
        query.append(" order by last_update desc");
        
        return baseDao.find(query.toString(), CustomerAddress.class);
    }

}
