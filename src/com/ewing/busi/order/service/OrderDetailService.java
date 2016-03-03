package com.ewing.busi.order.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.order.dao.OrderDetailDao;
import com.ewing.busi.order.dto.OrderInfoDetailResp;
import com.ewing.utils.FileUrlUtil;

@Repository("orderDetailService")
public class OrderDetailService extends BaseService {
    
    @Resource
    private OrderDetailDao orderDetailDao;

    
    public List<OrderInfoDetailResp> findByOrderIdAndCusId(Integer orderId, Integer cusId){
        if(null == orderId){
            return Collections.emptyList();
        }
        
        List<OrderInfoDetailResp> list = orderDetailDao.findByOrderIdAndCusId(orderId, cusId);
        for(OrderInfoDetailResp resp : list){
          resp.setIcon(FileUrlUtil.convertResourceUrl(resp.getIcon()));
        }
        return list;
    }
    
}
