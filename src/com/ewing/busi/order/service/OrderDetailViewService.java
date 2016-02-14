package com.ewing.busi.order.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.order.dao.OrderDetailViewDao;
import com.ewing.busi.order.model.OrderDetailView;

@Repository("orderDetailViewService")
public class OrderDetailViewService extends BaseService {
    
    @Resource
    private OrderDetailViewDao orderDetailViewDao;

    
    
    public List<OrderDetailView> findByOrderId(Integer orderId){
        if(null == orderId){
            return Collections.emptyList();
        }
        
        return orderDetailViewDao.findByOrderId(orderId);
    }
    
}
