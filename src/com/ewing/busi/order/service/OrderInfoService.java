package com.ewing.busi.order.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import com.ewing.busi.order.constants.OrderStatus;
import com.ewing.busi.order.dao.OrderInfoDao;
import com.ewing.busi.order.dto.ConfirmOrderReq;
import com.ewing.busi.order.dto.ConfirmOrderReq.Item;
import com.ewing.busi.order.dto.LightOrderInfoResp;
import com.ewing.busi.order.dto.OrderInfoDetailResp;
import com.ewing.busi.order.model.OrderDetail;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.common.constants.IsEff;
import com.ewing.common.constants.ResponseCode;
import com.ewing.common.exception.BusinessException;
import com.ewing.core.app.service.BaseModelService;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;
import com.google.common.collect.Lists;

@Repository("orderInfoService")
public class OrderInfoService {

    @Resource
    private BaseDao baseDao;

    @Resource
    private BaseModelService baseModelService;

    @Resource
    private OrderInfoDao orderInfoDao;

    @Resource
    private OrderDetailService orderDetailService;

    @Resource
    private WebResourceService webResourceService;

    /**
     * 根据客户id查询 购物车列表
     * 
     * @param cusId 客户ID
     * @param status 订单状态
     */
    public List<LightOrderInfoResp> queryByCusId(Integer cusId, String status, Integer page,
            Integer pageSize) {
        Validate.notNull(cusId, "客户id不能为空");
        Validate.notNull(page, "page不能为空");
        Validate.notNull(pageSize, "pageSize不能为空");

        List<LightOrderInfoResp> dtoList = Lists.newArrayList();
        List<OrderInfo> orderList = orderInfoDao.queryByCusId(cusId, status, page, pageSize);
        for (OrderInfo order : orderList) {
            List<OrderInfoDetailResp> detailDtoList = orderDetailService.findByOrderIdAndCusId(order.getId(), cusId);
            if(CollectionUtils.isEmpty(detailDtoList)){
                continue;
            }
            
            dtoList.add(new LightOrderInfoResp(order, detailDtoList));
        }

        return dtoList;
    }

    /**
     * 
     * @param orderId
     * @param cusId 商户Id
     * @author Joeson
     */
    public List<OrderInfoDetailResp> getByIdAndCusId(Integer orderId, Integer cusId) {
        Validate.notNull(orderId, "orderId不能为空");

        return orderDetailService.findByOrderIdAndCusId(orderId, cusId);
    }

    /**
     * 确认订单
     * 
     * @param req
     * @author Joeson
     */
    public void confirmOrder(ConfirmOrderReq req) {
        Validate.notNull(req, "入参不能为空");

        Integer orderId = null;
        float totalPrice = 0f;
        for (Item item : req.getList()) {
            try {
                OrderDetail detail = baseModelService
                        .findOne(item.getDetailId(), OrderDetail.class);
                detail.setItemCount(item.getItemCount());
                detail.setStatus(OrderStatus.WAIT_PAY.getValue());
                detail.setTotalPrice(orderDetailService.analysyTotal(detail));
                totalPrice = totalPrice + detail.getTotalPrice();

                orderId = detail.getOrderId();
                baseDao.update(detail);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        try {
            OrderInfo order = baseModelService.findOne(orderId, OrderInfo.class);
            order.setTotalPrice(totalPrice);
            order.setStatus(OrderStatus.WAIT_PAY.getValue());
            baseDao.update(order);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消order,如果order为已付款、待
     * 
     * @param orderId
     * @author Joeson
     */
    public void cancelOrder(Integer orderId) {
        Validate.notNull(orderId, "orderId不能为空");

        OrderInfo order = baseDao.findOne(orderId, OrderInfo.class);
        Validate.notNull(order, "order不能为空");
        //如果状态不为待付款或已收货，不能关闭订单
        if (!(ObjectUtils.equals(order.getStatus(), OrderStatus.WAIT_PAY.getValue()) || ObjectUtils
                .equals(order.getStatus(), OrderStatus.RECEIVEED.getValue()))) {
            throw new BusinessException(ResponseCode.BIZ_STATUS_ERROR, "当前状态不能关闭订单");
        }

        order.setIseff(IsEff.INEFFECTIVE.getValue());
        baseDao.update(order);

        return;
    }

}