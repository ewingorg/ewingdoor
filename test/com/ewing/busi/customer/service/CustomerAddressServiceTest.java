package com.ewing.busi.customer.service;

import java.util.List;

import org.junit.Test;

import com.ewing.busi.customer.constants.AddressDefault;
import com.ewing.busi.customer.dto.AddressDetailDto;
import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.customer.dto.LightAddressInfoResp;
import com.ewing.core.factory.SpringCtx;
import com.ewing.util.JsonUtils;

public class CustomerAddressServiceTest {
    
    
//    @Test
    public void testAdd() throws Exception {
        try {
            CustomerAddressService customerAddressService = (CustomerAddressService) SpringCtx
                    .getByBeanName("customerAddressService");
            
            for(int i = 0; i<3; i++){
            AddressDetailDto dto = new AddressDetailDto();
            dto.setCustomerId(10);
            dto.setProvince("广东省");
            dto.setCity("广州");
            dto.setRegion("天河区");
            dto.setAddress("潭村");
            dto.setPhone("1882641677" + i);
            dto.setIsDefault(AddressDefault.DEFAULT.getValue());
            dto.setIseff('1');
            
            customerAddressService.add(dto);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testQueryByCusId(){
        try {
            CustomerAddressService customerAddressService = (CustomerAddressService) SpringCtx
                    .getByBeanName("customerAddressService");
            
            LightAddressInfoReq dto = new LightAddressInfoReq();
            dto.setCusId(10);
            
            List<LightAddressInfoResp> list = customerAddressService.queryByCusId(dto.getCusId());
            System.out.println(JsonUtils.toJson(list));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
