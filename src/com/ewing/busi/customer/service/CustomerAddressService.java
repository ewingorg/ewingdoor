package com.ewing.busi.customer.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.customer.constants.AddressDefault;
import com.ewing.busi.customer.dao.CustomerAddressDao;
import com.ewing.busi.customer.dto.AddressDetailDto;
import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.customer.dto.LightAddressInfoResp;
import com.ewing.busi.customer.model.CustomerAddress;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.util.BeanCopy;
import com.ewing.util.DateUtil;
import com.ewing.util.IntegerUtil;

@Repository("customerAddressService")
public class CustomerAddressService extends BaseService{
    
    @Resource
    private BaseDao baseDao;
    @Resource
    private CustomerAddressDao customerAddressDao;

    /**
     * 根据客户id查询 购物车列表
     * @param cusId
     * @author Joeson
     */
    public List<LightAddressInfoResp> queryByCusId(Integer cusId) {
        checkFalse(IntegerUtil.nullOrZero(cusId), "cusId不能为空");
        
        List<CustomerAddress> list =  customerAddressDao.queryByCusId(cusId);
        return BeanCopy.copy(list, LightAddressInfoResp.class);
    }

    /**
     * 添加地址
     * @param req
     * @return
     * @author Joeson
     */
    public void add(AddressDetailDto req) {
        checkNotNull(req, "请求参数不能为空");
        
        CustomerAddress address = null;
        try{
            address = new CustomerAddress();
            BeanCopy.copy(address, req, true);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        baseDao.save(address);
    }
}
