package com.ewing.busi.order.helper;

import org.apache.commons.lang.StringUtils;

import com.ewing.busi.customer.dto.LightAddressInfoResp;
import com.ewing.busi.order.constants.OrderStatus;
import com.ewing.busi.order.model.OrderCart;
import com.ewing.busi.order.model.OrderDetail;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.busi.resource.model.WebResourcePrice;
import com.ewing.common.constants.IsEff;
import com.ewing.utils.IntegerUtils;

/**
 * 订单辅助类
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年3月1日
 */
public class OrderHelper {


  /**
   * 场景：客户在购物车中进行结算时候，购物车中的单的单件会转化为具体的对象 将购物车对象 转化为 订单详情对象，
   * 
   * @param cart
   * @param bizId
   * @param orderId
   * @author Joeson
   * @param orderId
   */
  public static OrderDetail initOrderDetail(OrderCart cart, String bizId, Integer itemCount,
      Integer orderId) {
    if (null == cart) {
      return null;
    }

    OrderDetail detail = new OrderDetail();
    detail.setOrderId(orderId);
    detail.setCustomerId(cart.getCustomerId());
    detail.setBizId(bizId);
    detail.setUserId(cart.getUserId());
    detail.setResourceId(cart.getResourceId());
    detail.setPriceId(cart.getPriceId());
    detail.setItemCount(itemCount);
    detail.setUnitPrice(cart.getUnitPrice());
    // detail.setCargoPrice( );
    detail.setOrderId(orderId);
    detail.setTotalPrice(analysyTotal(detail));
    detail.setStatus(OrderStatus.INIT.getValue());
    detail.setIseff(IsEff.EFFECTIVE.getValue());

    // @TODO设置物流信息
    // WebResource resource = webResourceService.findById(detail.getResourceId(),
    // WebResource.class);
    // if(null != resource){
    // detail.setCargoPrice(resource.get);
    // }

    return detail;
  }

  /**
   * @author Joeson
   */
  public static OrderDetail initOrderDetail(Integer orderId, Integer priceId, Integer cusId, String bizId, Integer count, WebResource resource, WebResourcePrice price) {
    if (IntegerUtils.nullOrZero(orderId) || IntegerUtils.nullOrZero(priceId)
        || IntegerUtils.nullOrZero(cusId) || StringUtils.isEmpty(bizId) || null == resource) {
      return null;
    }

    count = null == count ? 1 : count;
    
    OrderDetail detail = new OrderDetail();
    detail.setOrderId(orderId);
    detail.setCustomerId(cusId);
    detail.setBizId(bizId);
    detail.setUserId(resource.getUserId());
    detail.setResourceId(resource.getId());
    detail.setPriceId(priceId);
    detail.setItemCount(count);
    detail.setUnitPrice(null == price ? resource.getPrice() : price.getPrice());
    detail.setCargoPrice(count);
    detail.setOrderId(orderId);
    detail.setTotalPrice(analysyTotal(detail));
    detail.setStatus(OrderStatus.INIT.getValue());
    detail.setIseff(IsEff.EFFECTIVE.getValue());

    // @TODO设置物流信息
    // WebResource resource = webResourceService.findById(detail.getResourceId(),
    // WebResource.class);
    // if(null != resource){
    // detail.setCargoPrice(resource.get);
    // }

    return detail;

  }

  /**
   * 结算一个订单详情的总价
   * 
   * @author Joeson
   */
  public static float analysyTotal(OrderDetail detail) {
    if (null == detail) {
      return 0f;
    }

    float resourcePrice = detail.getUnitPrice() * detail.getItemCount();
    return resourcePrice + detail.getCargoPrice();
  }
  
  public static LightAddressInfoResp toAddress(OrderInfo order){
    if(null == order){
      return null;
    }
    
    LightAddressInfoResp addr = new LightAddressInfoResp();
    addr.setReceiver(order.getReceiver());
    addr.setProvince(order.getProvince());
    addr.setCity(order.getCity());
    addr.setRegion(order.getRegion());
    addr.setAddress(order.getAddress());
    addr.setPostCode(order.getPostCode());
    addr.setPhone(order.getPhone());
    
    return addr;
  }
}
