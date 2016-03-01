package com.ewing.busi.customer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.customer.constants.AddressDefault;
import com.ewing.busi.customer.model.CustomerAddress;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.util.PageBean;
import com.ewing.utils.PageUtil;

@Repository("customerAddressDao")
public class CustomerAddressDao {

    @Resource
    private BaseDao baseDao;

    /**
     * 根据客户id查询 购物车列表
     * 
     * @param cusId
     * @author Joeson
     */
    public List<CustomerAddress> queryByCusId(Integer cusId) {
        StringBuilder query = new StringBuilder();
        query.append("customer_id=").append(cusId);
        query.append(" and iseff = ").append(IsEff.EFFECTIVE.getValue());
        query.append(" order by last_update desc");

        return baseDao.find(query.toString(), CustomerAddress.class);
    }
    
    /**
     * 根据客户id查询 购物车列表
     * 
     * @param cusId
     * @author Joeson
     * @param pageSize
     * @param page
     */
    public List<CustomerAddress> queryByCusId(Integer cusId, Integer page, Integer pageSize) {
        StringBuilder query = new StringBuilder();
        query.append("customer_id=").append(cusId);
        query.append(" and iseff = ").append(IsEff.EFFECTIVE.getValue());
        query.append(" order by last_update desc");

        PageBean<CustomerAddress> pageBean = baseDao.pageQuery(query.toString(), pageSize, page,
                CustomerAddress.class);
        return pageBean.getResult();
    }

    /**
     * 查找默认的地址，如果没有，就找最新的一个地址
     * @param cusId
     * @return
     * @author Joeson
     */
    public CustomerAddress findDefaultAddress(Integer cusId) {
        StringBuilder query = new StringBuilder();
        query.append(" customer_id = ").append(cusId);
        query.append(" and is_default='").append(AddressDefault.DEFAULT.getValue()).append("'");
        query.append(" and iseff='").append(IsEff.EFFECTIVE.getValue()).append("'");

        CustomerAddress defaultAddr = baseDao.findOne(query.toString(), CustomerAddress.class);
        if (null == defaultAddr) {
            query.setLength(0);
            query.append(" iseff='").append(IsEff.EFFECTIVE.getValue()).append("'");
            query.append(" order by last_update desc");
            defaultAddr = baseDao.findOne(query.toString(), CustomerAddress.class);
        }
        
        return defaultAddr;
    }

    public CustomerAddress findByIdAndCusId(Integer id, Integer cusId) {
        StringBuilder query = new StringBuilder();
        query.append(" id= ").append(id);
        query.append(" and customer_id = ").append(cusId);

        return baseDao.findOne(query.toString(), CustomerAddress.class);
    }

}
