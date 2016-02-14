package com.ewing.busi.order.service;

import java.util.List;

import org.junit.Test;

import com.ewing.busi.order.dto.LightOrderInfoResp;
import com.ewing.core.factory.SpringCtx;
import com.ewing.util.JsonUtils;

public class OrderInfoServiceTest {

    @Test
    public void test(){
        OrderInfoService orderInfoService = (OrderInfoService) SpringCtx
                .getByBeanName("orderInfoService");
        
        List<LightOrderInfoResp> list = orderInfoService.queryByCusId(10, null, 1, 10);
        
        System.out.println(JsonUtils.toJson(list));
    }
}
