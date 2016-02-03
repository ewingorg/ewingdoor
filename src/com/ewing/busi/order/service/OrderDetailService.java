package com.ewing.busi.order.service;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.order.model.OrderDetail;

public class OrderDetailService extends BaseService {

    
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

}
