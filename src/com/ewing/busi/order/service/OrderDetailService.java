package com.ewing.busi.order.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.order.dao.OrderDetailDao;
import com.ewing.busi.order.dto.OrderInfoDetailResp;

@Repository("orderDetailService")
public class OrderDetailService extends BaseService {
    
    @Resource
    private OrderDetailDao orderDetailDao;

    
    public List<OrderInfoDetailResp> findByOrderIdAndCusId(Integer orderId, Integer cusId){
        if(null == orderId){
            return Collections.emptyList();
        }
        
        return orderDetailDao.findByOrderIdAndCusId(orderId, cusId);
    }
    
}
