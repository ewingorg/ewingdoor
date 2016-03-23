package com.ewing.busi.customer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.customer.model.Customer;
import com.ewing.busi.customer.model.CustomerThirdAccount;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;

/**
 * 客户DAO
 * 
 * @author Joeson
 * @createDate 2016年2月16日
 * 
 */
@Repository("customerThirdAccountDao")
public class CustomerThirdAccountDao {
  @Resource
  private BaseDao baseDao;

  /**
   * 根据userId和平台id查找
   * @param userId
   * @param platId
   * @return
   * @author Joeson
   */
  public CustomerThirdAccount findByUserIdAndPlatId(String userId, Integer platId) {
    StringBuilder query = new StringBuilder();
    query.append(" user_id = '").append(userId).append("'");
    query.append(" and third_platform = ").append(platId);
    
    return baseDao.findOne(query.toString(), CustomerThirdAccount.class);
  }

}
