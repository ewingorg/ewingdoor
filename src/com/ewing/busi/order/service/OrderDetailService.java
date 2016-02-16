package com.ewing.busi.order.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.order.dao.OrderDetailDao;
import com.ewing.busi.order.dto.OrderInfoDetailResp;
import com.ewing.busi.order.model.OrderDetail;

@Repository("orderDetailService")
public class OrderDetailService extends BaseService {
    
    @Resource
    private OrderDetailDao orderDetailDao;

    
    /**
     * 结算一个订单详情的总价
     * @author Joeson
     */
    public float analysyTotal(OrderDetail detail) {
        if (null == detail) {
            return 0f;
        }

        float resourcePrice = detail.getUnitPrice() * detail.getItemCount();
        return resourcePrice + detail.getCargoPrice();
    }
    
    public List<OrderInfoDetailResp> findByOrderIdAndCusId(Integer orderId, Integer cusId){
        if(null == orderId){
            return Collections.emptyList();
        }
        
        return orderDetailDao.findByOrderIdAndCusId(orderId, cusId);
    }
    
}
