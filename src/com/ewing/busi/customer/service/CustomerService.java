package com.ewing.busi.customer.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.customer.dao.CustomerDao;
import com.ewing.busi.customer.model.Customer;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;
import com.ewing.utils.BeanCopy;

@Repository("customerService")
public class CustomerService extends BaseService {

    @Resource
    private BaseDao baseDao;
    @Resource
    private CustomerDao customerDao;

    public Customer queryById(Integer id) {
        checkNotNull(id, "id不能为空");

        return baseDao.findOne(id, Customer.class);
    }

    /**
     * 保存客户信息
     * 
     * @param customer
     * @return
     * @author Joeson
     */
    public void add(Customer customer) {
        checkNotNull(customer, "请求参数不能为空");

        customer.setIseff(IsEff.EFFECTIVE.getValue());

        baseDao.save(customer);
    }

    /**
     * update 操作
     * 
     * @param newCus
     * @author Joeson
     */
    public void update(Customer newCus) {
        checkNotNull(newCus, "入参不能为空");

        Customer old = queryById(newCus.getId());
        checkNotNull(old, String.format("找不到对应的客户信息[id=%d]", newCus.getId()));
        BeanCopy.copy(old, newCus, true);

        baseDao.update(old);
    }

    /**
     * 查找消费者用户
     * 
     * @param userName
     * @return
     * @throws DaoException
     */
    public List<Customer> findUser(String userName) throws DaoException {
        if (StringUtils.isEmpty(userName))
            throw new IllegalArgumentException("userName should not be null");
        return customerDao.findUser(userName);
    }
}
