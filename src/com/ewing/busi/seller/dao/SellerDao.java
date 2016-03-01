package com.ewing.busi.seller.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.core.jdbc.BaseDao;

/**
 * seller操作类
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月20日
 *
 */
@Repository("sellerDao")
public class SellerDao {
    @Resource
    private BaseDao baseDao;

    
    
}
