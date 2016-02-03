package com.ewing.busi.customer.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.customer.model.Customer;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.util.BeanCopy;

@Repository("customerService")
public class CustomerService extends BaseService {
    
    @Resource
    private BaseDao baseDao;
    
    public Customer queryById(Integer id){
        checkNotNull(id, "id不能为空");
        
        return baseDao.findOne(id, Customer.class);
    }

    /**
     * 保存客户信息
     * @param customer
     * @return
     * @author Joeson
     */
    public void add(Customer customer) {
        checkNotNull(customer, "请求参数不能为空");
        
        customer.setIseff(IsEff.EFFECTIVE.getValue());;
        baseDao.save(customer);
    }
    
    /**
     * update 操作
     * @param newCus
     * @author Joeson
     */
    public void update(Customer newCus){
        checkNotNull(newCus, "入参不能为空");
        
        Customer old = queryById(newCus.getId());
        checkNotNull(old, String.format("找不到对应的客户信息[id=%d]", newCus.getId()));
        BeanCopy.copy(old, newCus, true);

        baseDao.update(old);
    }
}
