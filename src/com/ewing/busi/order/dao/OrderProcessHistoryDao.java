package com.ewing.busi.order.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.core.jdbc.BaseDao;


@Repository("orderProcessHistoryDao")
public class OrderProcessHistoryDao {
  
  @Resource
  private BaseDao baseDao;
  
  

}
