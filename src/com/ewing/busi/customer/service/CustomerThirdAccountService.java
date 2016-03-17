package com.ewing.busi.customer.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.customer.dao.CustomerDao;
import com.ewing.busi.customer.dao.CustomerThirdAccountDao;
import com.ewing.busi.customer.model.Customer;
import com.ewing.busi.customer.model.CustomerThirdAccount;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;
import com.ewing.utils.BeanCopy;

@Repository("customerThirdAccountService")
public class CustomerThirdAccountService extends BaseService {

    @Resource
    private BaseDao baseDao;
    @Resource
    private CustomerThirdAccountDao customerThirdAccountDao;
    
    public CustomerThirdAccount findByUserIdAndPlatId(String userId, Integer platId){
      if(StringUtils.isEmpty(userId) || null == platId){
        return null;
      }
      
      return customerThirdAccountDao.findByUserIdAndPlatId(userId, platId);
    }
}
