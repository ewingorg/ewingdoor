package com.ewing.busi.order.service;

import java.util.List;

import javax.annotation.Resource;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.order.dao.OrderInfoDao;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.util.IntegerUtil;

public class OrderInfoService extends BaseService{

    @Resource
    private BaseDao baseDao;
    
    @Resource
    private OrderInfoDao orderInfoDao;

    /**
     * 根据客户id查询 购物车列表
     * @param cusId
     * @author Joeson
     */
    public List<OrderInfo> queryByCusId(Integer cusId) {
        checkFalse(IntegerUtil.nullOrZero(cusId), "cusId不能为空");
        
        return orderInfoDao.queryByCusId(cusId);
    }

}