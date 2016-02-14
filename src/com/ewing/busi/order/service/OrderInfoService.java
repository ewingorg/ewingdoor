package com.ewing.busi.order.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import com.ewing.busi.order.constants.OrderStatus;
import com.ewing.busi.order.dao.OrderInfoDao;
import com.ewing.busi.order.dto.ConfirmOrderReq;
import com.ewing.busi.order.dto.ConfirmOrderReq.Item;
import com.ewing.busi.order.dto.LightOrderInfoResp;
import com.ewing.busi.order.dto.OrderInfoDetailResp;
import com.ewing.busi.order.model.OrderDetail;
import com.ewing.busi.order.model.OrderDetailView;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.core.app.service.BaseModelService;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;
import com.ewing.util.BeanCopy;
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
    
    @Resource
    private OrderDetailViewService orderDetailViewService;

    /**
     * 根据客户id查询 购物车列表
     * 
     * @param cusId
     * @author Joeson
     * @param status 
     * @param pageSize
     * @param page
     */
    public List<LightOrderInfoResp> queryByCusId(Integer cusId, Character status, Integer page, Integer pageSize) {
        Validate.notNull(cusId, "客户id不能为空");
        Validate.notNull(page, "page不能为空");
        Validate.notNull(pageSize, "pageSize不能为空");

        List<LightOrderInfoResp> dtoList = Lists.newArrayList();
        List<OrderInfo> orderList = orderInfoDao.queryByCusId(cusId, status, page, pageSize);
        for(OrderInfo order : orderList){
            List<OrderDetailView> viewList = orderDetailViewService.findByOrderId(order.getId());
            dtoList.add(new LightOrderInfoResp(order, viewList));
        }
        
        return dtoList;
    }

    public List<OrderInfoDetailResp> getById(Integer orderId) {
        Validate.notNull(orderId, "orderId不能为空");

        List<OrderDetail> detailList = orderDetailService.findByOrderId(orderId);
        List<OrderInfoDetailResp> dtoList = Lists.newArrayList();
        for (OrderDetail detail : detailList) {
            OrderInfoDetailResp dto = new OrderInfoDetailResp();
            BeanCopy.copy(dto, detail, true);

            WebResource resource = baseDao.findOne(detail.getResourceId(), WebResource.class);
            dto.setProductName(resource.getName());
            dto.setIcon(resource.getImageUrl());
            dtoList.add(dto);
        }

        return dtoList;
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

}