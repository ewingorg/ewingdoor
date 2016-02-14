package com.ewing.busi.order.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.model.OrderDetailView;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年2月14日
 *
 */
@Repository("orderDetailViewDao")
public class OrderDetailViewDao {

    @Resource
    private BaseDao baseDao;

    /**
     * 根据orderId查找
     * @param orderId
     * @author Joeson
     */
    public List<OrderDetailView> findByOrderId(Integer orderId) {
        StringBuilder query = new StringBuilder();
        query.append(" order_id = ").append(orderId);
        query.append(" and iseff = ").append(IsEff.EFFECTIVE.getValue());
        query.append(" order by id");
        
        return baseDao.find(query.toString(), OrderDetailView.class);
    }

}
