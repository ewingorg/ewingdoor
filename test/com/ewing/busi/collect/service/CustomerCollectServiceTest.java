package com.ewing.busi.collect.service;

import java.util.List;

import org.junit.Test;

import com.ewing.busi.collect.dto.AddCollectReq;
import com.ewing.busi.collect.dto.LightCollectResp;
import com.ewing.busi.customer.constants.AddressDefault;
import com.ewing.busi.customer.dto.AddressAddDto;
import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.customer.dto.LightAddressInfoResp;
import com.ewing.common.constants.IsEff;
import com.ewing.core.factory.SpringCtx;
import com.ewing.utils.JsonUtils;

public class CustomerCollectServiceTest {

//    @Test
    public void testAddCollect() throws Exception {
        try {
            CustomerCollectService customerCollectService = (CustomerCollectService) SpringCtx
                    .getByBeanName("customerCollectService");

            for (int i = 0; i < 3; i++) {
                AddCollectReq req = new AddCollectReq();
                req.setResId(7 + i);
                customerCollectService.addCollect(req.getResId(), 10);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByCusId() throws Exception {
        try {
            CustomerCollectService customerCollectService = (CustomerCollectService) SpringCtx
                    .getByBeanName("customerCollectService");

            List<LightCollectResp> list = customerCollectService.findByCusId(10, 1, 5);
            System.out.println(JsonUtils.toJson(list));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
