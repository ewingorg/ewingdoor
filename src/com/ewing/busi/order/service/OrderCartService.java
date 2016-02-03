package com.ewing.busi.order.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.order.dao.OrderCartDao;
import com.ewing.busi.order.model.OrderCart;
import com.ewing.busi.order.model.OrderDetail;
import com.ewing.busi.resource.dto.ProductDetailResp;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.util.IntegerUtil;
import com.google.common.collect.Lists;

@Repository("orderCartService")
public class OrderCartService extends BaseService{

    @Resource
    private BaseDao baseDao;
    
    @Resource
    private OrderCartDao orderCartDao;
    
    @Resource
    private WebResourceService webResourceService;
    
    @Resource
    private OrderDetailService orderDetailService; 

    /**
     * 根据客户id查询 购物车列表
     * @param cusId
     * @author Joeson
     */
    public List<OrderCart> queryByCusId(Integer cusId) {
        checkFalse(IntegerUtil.nullOrZero(cusId), "cusId不能为空");
        
        return orderCartDao.queryByCusId(cusId);
    }
    
    /**
     * 
     * @param cartList
     * @return
     * @author Joeson
     */
    public List<OrderDetail> toOrder(List<OrderCart> cartList){
        if(CollectionUtils.isEmpty(cartList)){
            return Collections.EMPTY_LIST;
        }
        
        List<OrderDetail> detailList = Lists.newArrayList();
        for(OrderCart cart : cartList)
        {
            //@TODO填写 bizId 以及 orderId
            detailList.add(toOrderDetail(cart, StringUtils.EMPTY, 0));
        }
        
        return detailList;
    }
    
    /**
     * 场景：客户在购物车中进行结算时候，购物车中的单的单件会转化为具体的对象
     * 将购物车对象 转化为 订单详情对象，
     * @param cart
     * @param bizId
     * @param orderId
     * @author Joeson
     */
    private OrderDetail toOrderDetail(OrderCart cart, String bizId,Integer orderId){
        if(null == cart){
            return null;
        }
        
        OrderDetail detail = new OrderDetail();
        detail.setCustomerId(cart.getCustomerId());
        detail.setUserId(cart.getUserId());
        detail.setResourceId(cart.getResourceId());
        detail.setItemCount(cart.getItemCount());
        detail.setUnitPrice(cart.getUnitPrice());
        detail.setTotalPrice(cart.getTotalPrice());
        detail.setIseff(cart.getIseff());
        
        detail.setBizId(bizId);
        detail.setOrderId(IntegerUtil.toInt(orderId));

        //@TODO设置物流信息
//        WebResource resource = webResourceService.findById(detail.getResourceId(), WebResource.class);
//        if(null != resource){
//            detail.setCargoPrice(resource.get);
//        }

        //计算总价
        detail.setTotalPrice(orderDetailService.analysyTotal(detail));
        
        return detail;
    }
}
