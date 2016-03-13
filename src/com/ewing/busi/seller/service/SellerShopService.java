package com.ewing.busi.seller.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.seller.dao.SellerShopDao;
import com.ewing.busi.seller.model.SellerShop;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.utils.IntegerUtils;

@Repository("sellerShopService")
public class SellerShopService {
  @Resource
  private BaseDao baseDao;
  @Resource
  private SellerShopDao sellerShopDao;
  
  /**
   * @param shopId
   * @return
   * @author Joeson
   */
  public SellerShop findShop(Integer shopId){
    if(IntegerUtils.nullOrZero(shopId)){
      return null;
    }
    
    return baseDao.findOne(shopId, SellerShop.class);
  }
  
  public String findName(Integer shopId){
    SellerShop shop = findShop(shopId);
    if(null == shop){
      return StringUtils.EMPTY;
    }
    
    return shop.getShopName();
  }

}
