package com.ewing.busi.address.dao;

import org.junit.Test;

import com.ewing.busi.collect.dto.AddCollectReq;
import com.ewing.busi.collect.service.CustomerCollectService;
import com.ewing.busi.customer.dao.CustomerAddressDao;
import com.ewing.busi.customer.model.CustomerAddress;
import com.ewing.busi.customer.service.CustomerAddressService;
import com.ewing.core.factory.SpringCtx;
import com.ewing.utils.JsonUtils;


public class CustomerAddressDaoTest {

  @Test
  public void testFindDefaultAddress(){
    try {
      CustomerAddressDao customerAddressDao = (CustomerAddressDao) SpringCtx
              .getByBeanName("customerAddressDao");
      
      CustomerAddress addr = customerAddressDao.findDefaultAddress(10);
      
      if(null != addr){
        System.out.println(JsonUtils.toJson(addr));
      }
      
  } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
  }
  }
}
