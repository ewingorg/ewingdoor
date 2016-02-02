package com.ewing.busi.order.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.order.dao.OrderCartDao;
import com.ewing.busi.order.model.OrderCart;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.util.IntegerUtil;

@Repository("orderCartService")
public class OrderCartService extends BaseService{

    @Resource
    private BaseDao baseDao;
    
    @Resource
    private OrderCartDao orderCartDao;

    /**
     * 根据客户id查询 购物车列表
     * @param cusId
     * @author Joeson
     */
    public List<OrderCart> queryByCusId(Integer cusId) {
        checkFalse(IntegerUtil.nullOrZero(cusId), "cusId不能为空");
        return orderCartDao.queryByCusId(cusId);
    }

}
