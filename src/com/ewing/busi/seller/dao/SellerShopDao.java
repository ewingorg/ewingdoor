package com.ewing.busi.seller.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.seller.model.SellerShop;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * sellshop dao操作类
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月20日
 */
@Repository("sellerShopDao")
public class SellerShopDao {

    @Resource
    private BaseDao baseDao;
    
    /**
     * 查询商铺
     * @param shopId
     * @return
     */
    public SellerShop findShop(Integer shopId) {
        StringBuilder query = new StringBuilder();
        query.append(" and id = ").append(shopId);
        query.append(" and iseff = '").append(IsEff.EFFECTIVE.getValue()).append("'");
        return baseDao.findOne(query.toString(), SellerShop.class);
    }
}
