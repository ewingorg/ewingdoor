package com.ewing.busi.order.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.model.OrderCart;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.util.PageUtil;

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
     */
    public List<OrderCart> queryByCusId(Integer cusId, Integer page, Integer pageSize) {
        StringBuilder query = new StringBuilder();
        query.append("customer_id=").append(cusId);
        query.append(" and iseff = ").append(IsEff.EFFECTIVE.getValue());
        query.append(" order by last_update desc");
        query.append(" limit ").append(PageUtil.getOffset(page, pageSize)).append(" , ").append(PageUtil.getLimit(page, pageSize));
        
        return baseDao.find(query.toString(), OrderCart.class);
        
//        PageBean<OrderCart> pageBean =  baseDao.pageQuery(query.toString(), pageSize, page, OrderCart.class);
//        return null!= pageBean ? pageBean.getResult() : Collections.EMPTY_LIST;
    }

}
