package com.ewing.busi.customer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.customer.constants.AddressDefault;
import com.ewing.busi.customer.model.CustomerAddress;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.util.PageBean;
import com.ewing.utils.PageUtil;

@Repository("customerAddressDao")
public class CustomerAddressDao {

    @Resource
    private BaseDao baseDao;

    /**
     * 根据客户id查询 购物车列表
     * @param cusId
     * @author Joeson
     * @param pageSize 
     * @param page 
     */
    public List<CustomerAddress> queryByCusId(Integer cusId, Integer page, Integer pageSize) {
        StringBuilder query = new StringBuilder();
        query.append("customer_id=").append(cusId);
        query.append(" order by last_update desc");
        
        PageBean<CustomerAddress> pageBean = baseDao.pageQuery(query.toString(), pageSize, page, CustomerAddress.class);
        return pageBean.getResult();
    }

    public CustomerAddress findDefaultAddress(Integer cusId) {
        StringBuilder query = new StringBuilder();
        query.append("is_default='").append(AddressDefault.DEFAULT.getValue()).append("'");
        
        return baseDao.findOne(query.toString(), CustomerAddress.class);
    }

    public CustomerAddress findByIdAndCusId(Integer id, Integer cusId) {
        StringBuilder query = new StringBuilder();
        query.append(" id='").append(id);
        query.append(" and customer_id = ").append(cusId);
        
        return baseDao.findOne(query.toString(), CustomerAddress.class);
    }

}
