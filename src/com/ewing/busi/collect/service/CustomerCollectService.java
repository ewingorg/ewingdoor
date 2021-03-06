package com.ewing.busi.collect.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import com.ewing.busi.collect.dao.CustomerCollectDao;
import com.ewing.busi.collect.dto.LightCollectResp;
import com.ewing.busi.collect.model.CustomerCollect;
import com.ewing.busi.customer.model.CustomerAddress;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.utils.IntegerUtils;

/**
 * 
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月23日
 */
@Repository("customerCollectService")
public class CustomerCollectService {

    @Resource
    private BaseDao baseDao;
    @Resource
    private CustomerCollectDao customerCollectDao;

    public List<LightCollectResp> findByCusId(Integer cusId, Integer page, Integer pageSize) {
        Validate.notNull(cusId, "cusId不能为空");

        return customerCollectDao.findByCusId(cusId, page, pageSize);
    }

    /**
     * 保存到收藏夹
     * @author Joeson
     */
    public boolean addCollect(Integer cusId, Integer resId) {
        Validate.notNull(cusId, "cusId不能为空");
        Validate.notNull(resId, "resId不能为空");
        
        CustomerCollect collect = customerCollectDao.findByCusIdAndResId(cusId, resId);
        if(null != collect){
            collect.setIseff(IsEff.EFFECTIVE.getValue());
            collect.setCreateTime(new Date());
            baseDao.update(collect);
        }else{
            collect = new CustomerCollect();
            collect.setCustomerId(cusId);
            collect.setResourceId(resId);
            collect.setIseff(IsEff.EFFECTIVE.getValue());
            baseDao.save(collect);
        }
        
        return true;
    }

    public CustomerCollect findByCusIdAndResId(Integer cusId, Integer resourceId) {
      if(IntegerUtils.nullOrZero(cusId) || IntegerUtils.nullOrZero(resourceId)){
        return null;
      }
      
      return customerCollectDao.findByCusIdAndResId(cusId, resourceId);
    }

    /**
     * 取消购物车
     * @param cusId
     * @param resId
     * @author Joeson
     */
    public boolean delCollect(Integer cusId, Integer resId) {
      CustomerCollect collect = customerCollectDao.findByCusIdAndResId(cusId, resId);
      if(null == collect){
        return true;
      }
      
      collect.setIseff(IsEff.INEFFECTIVE.getValue());
      baseDao.update(collect);
      return true;
      
    }

}
