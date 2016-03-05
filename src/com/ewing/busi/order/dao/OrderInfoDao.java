package com.ewing.busi.order.dao;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.order.model.OrderInfo;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.util.PageBean;
import com.ewing.utils.PageUtil;

@Repository("orderInfoDao")
public class OrderInfoDao {

    @Resource
    private BaseDao baseDao;

    /**
     * 根据客户id查询 订单列表
     * 
     * @param cusId
     * @author Joeson
     * @param statusList
     */
    @SuppressWarnings("unchecked")
    public List<OrderInfo> queryByCusIdAndStatus(Integer cusId, List<String> statusList, Integer page, Integer pageSize) {
        StringBuilder query = new StringBuilder();
        query.append("customer_id=").append(cusId);
        query.append(" and iseff = ").append(IsEff.EFFECTIVE.getValue());
        if (CollectionUtils.isNotEmpty(statusList)) {
            query.append(" and status in (").append(StringUtils.join(statusList.toArray(), ",")).append(")");
        }
        
        query.append(" order by last_update desc");
        query.append(" limit ").append(PageUtil.getLimit(page, pageSize));

        PageBean<OrderInfo> pageBean = baseDao.pageQuery(query.toString(), pageSize, page,
                OrderInfo.class);
        return null != pageBean ? pageBean.getResult() : Collections.EMPTY_LIST;
    }

}
