package com.ewing.busi.customer.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import com.ewing.busi.customer.constants.AddressDefault;
import com.ewing.busi.customer.dao.CustomerAddressDao;
import com.ewing.busi.customer.dto.AddressAddDto;
import com.ewing.busi.customer.dto.AddressDetailResp;
import com.ewing.busi.customer.dto.LightAddressInfoResp;
import com.ewing.busi.customer.model.CustomerAddress;
import com.ewing.common.constants.IsEff;
import com.ewing.core.app.service.BaseModelService;
import com.ewing.core.jdbc.DaoException;
import com.ewing.util.BeanCopy;
import com.ewing.util.JsonUtils;

/**
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年2月3日
 *
 */
@Repository("customerAddressService")
public class CustomerAddressService {

    @Resource
    private BaseModelService baseModelService;
    @Resource
    private CustomerAddressDao customerAddressDao;

    /***** 检查是否当前开发者 *************/
    /**
     * 根据客户id查询 购物车列表
     * 
     * @param cusId
     * @author Joeson
     */
    public List<LightAddressInfoResp> queryByCusId(Integer cusId) {
        Validate.notNull(cusId, "cusId不能为空");

        List<CustomerAddress> list = customerAddressDao.queryByCusId(cusId);
        return BeanCopy.copy(list, LightAddressInfoResp.class);
    }

    /**
     * 添加地址
     * 
     * @param req
     * @return
     * @author Joeson
     */
    public boolean save(AddressAddDto req) {
        Validate.notNull(req, "请求参数不能为空");

        CustomerAddress address = null;
        try {
            // 如果存在id，则进行保存，否则进行update
            if (null != req.getId()) {
                address = baseModelService.findOne(req.getId(), CustomerAddress.class);
                BeanCopy.copy(address, req, true);
                baseModelService.update(address);
            } else {
                address = new CustomerAddress();
                BeanCopy.copy(address, req, true);
                
                //@TODO设置默认的customerId
                address.setCustomerId(10);
                address.setIsDefault(AddressDefault.UN_DEFAULT.getValue());
                address.setIseff(IsEff.EFFECTIVE.getValue());
                baseModelService.save(address);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 根据id查找地址信息
     * 
     * @author Joeson
     */
    public AddressDetailResp findById(Integer id) {
        Validate.notNull(id, "id不能为空");

        try {
            CustomerAddress address = baseModelService.findOne(id, CustomerAddress.class);
            AddressDetailResp resp = new AddressDetailResp();
            BeanUtils.copyProperties(resp, address);

            JsonUtils.toJson(resp);
            return resp;
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 设置地址为默认的，则其他的地址就不是默认了
     * 
     * @param id
     * @author Joeson
     * @throws DaoException
     */
    public void setDefaultAddress(Integer id) throws DaoException {
        Validate.notNull(id, "id不能为空");

        // 设置地址为默认
        CustomerAddress address = baseModelService.findOne(id, CustomerAddress.class);
        Validate.notNull(address, String.format("找不到对应的地址信息[id=%d]", id));

        // 设置原来默认的地址为非默认
        CustomerAddress defAddress = findDefaultAddress(address.getCustomerId());
        if (null != defAddress) {
            defAddress.setIsDefault(AddressDefault.UN_DEFAULT.getValue());
            baseModelService.update(defAddress);
        }
        address.setIsDefault(AddressDefault.DEFAULT.getValue());
        baseModelService.update(address);

    }

    public CustomerAddress findDefaultAddress(Integer cusId) {
        Validate.notNull(cusId, "客户id不能为空");

        return customerAddressDao.findDefaultAddress(cusId);
    }
}
