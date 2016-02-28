package com.ewing.busi.order.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.resource.spi.IllegalStateException;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.order.constants.OrderStatus;
import com.ewing.busi.order.dao.OrderCartDao;
import com.ewing.busi.order.dto.LightOrderCartResp;
import com.ewing.busi.order.dto.SubmitCartReq;
import com.ewing.busi.order.dto.SubmitCartReq.Item;
import com.ewing.busi.order.model.OrderCart;
import com.ewing.busi.order.model.OrderDetail;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.common.constants.IsEff;
import com.ewing.core.app.service.BaseModelService;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;
import com.ewing.utils.BizGenerator;
import com.ewing.utils.FloatUtils;
import com.ewing.utils.IntegerUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Repository("orderCartService")
public class OrderCartService extends BaseService {

  @Resource
  private BaseDao baseDao;

  @Resource
  private OrderCartDao orderCartDao;

  @Resource
  private WebResourceService webResourceService;

  @Resource
  private OrderDetailService orderDetailService;

  @Resource
  BaseModelService baseModelService;

  private float analyseTotal(OrderCart cart) {
    if (null == cart) {
      return 0f;
    }

    return cart.getUnitPrice() * IntegerUtils.toInt(cart.getItemCount()) + cart.getCargoPrice();
  }

  /**
   * 根据客户id查询 购物车列表
   * 
   * @param cusId
   * @author Joeson
   * @param pageSize
   * @param page
   */
  public List<LightOrderCartResp> queryByCusId(Integer cusId, Integer page, Integer pageSize) {
    checkFalse(IntegerUtils.nullOrZero(cusId), "cusId不能为空");

    return orderCartDao.queryByCusId(cusId, page, pageSize);
  }

  /**
   * 场景：客户在购物车中进行结算时候，购物车中的单的单件会转化为具体的对象 将购物车对象 转化为 订单详情对象，
   * 
   * @param cart
   * @param bizId
   * @param orderId
   * @author Joeson
   * @param orderId
   */
  private OrderDetail toOrderDetail(OrderCart cart, String bizId, Integer itemCount, Integer orderId) {
    if (null == cart) {
      return null;
    }

    OrderDetail detail = new OrderDetail();
    detail.setOrderId(orderId);
    detail.setCustomerId(cart.getCustomerId());
    detail.setBizId(bizId);
    detail.setUserId(cart.getUserId());
    detail.setResourceId(cart.getResourceId());
    detail.setItemCount(itemCount);
    detail.setUnitPrice(cart.getUnitPrice());
    detail.setCargoPrice(cart.getItemCount());
    detail.setOrderId(orderId);
    detail.setTotalPrice(orderDetailService.analysyTotal(detail));
    detail.setStatus(OrderStatus.WAIT_PAY.getValue());
    detail.setIseff(cart.getIseff());

    // @TODO设置物流信息
    // WebResource resource = webResourceService.findById(detail.getResourceId(),
    // WebResource.class);
    // if(null != resource){
    // detail.setCargoPrice(resource.get);
    // }

    return detail;
  }

  /**
   * 进行购物车结算，返回订单id
   * 
   * @param cusId 客户id
   */
  public Integer balanceCart(SubmitCartReq req, Integer cusId) {
    Validate.notNull(req, "req不能为空");

    Map<Integer, Item> map = Maps.newHashMap();
    List<Integer> cartIdList = Lists.newArrayList();
    List<Item> list = req.getList();
    for (Item item : list) {
      cartIdList.add(item.getId());
      map.put(item.getId(), item);
    }

    // 找到对应的购物车信息
    List<OrderCart> cartList = orderCartDao.findByIdAndCusId(cartIdList, cusId);
    String bizId = BizGenerator.generateBizNum();
    List<Object> detailList = Lists.newArrayList();
    float totalPrice = 0f;

    OrderInfo orderInfo = new OrderInfo();
    orderInfo.setCustomerId(10);
    orderInfo.setUserId(0);
    orderInfo.setBizId(bizId);
    orderInfo.setCargoPrice(0f);
    orderInfo.setTotalPrice(0f);
    orderInfo.setStatus(OrderStatus.WAIT_PAY.getValue());
    orderInfo.setPhone("");
    orderInfo.setIseff(IsEff.EFFECTIVE.getValue());

    baseDao.save(orderInfo);

    for (OrderCart cart : cartList) {
      Item item = map.get(cart.getId());

      cart.setIseff(IsEff.INEFFECTIVE.getValue());
      baseDao.update(cart);

      OrderDetail detail = toOrderDetail(cart, bizId, item.getItemCount(), orderInfo.getId());
      detailList.add(detail);

      totalPrice = totalPrice + detail.getTotalPrice();
    }

    try {
      baseModelService.saveMuti(detailList);
    } catch (DaoException e) {
      e.printStackTrace();
    }

    // 设置总价
    orderInfo.setProductPrice(totalPrice);
    baseDao.update(orderInfo);

    return orderInfo.getId();
  }

  /**
   * 添加购物车
   * 
   * @param cusId 客户id
   * @param resourceId 资源id
   * @param count 购物车数量
   * @author Joeson
   * @throws IllegalStateException
   */
  public void addCart(Integer cusId, Integer resourceId, Integer count)
      throws IllegalStateException {
    Validate.notNull(cusId, "cusId不能为空");
    Validate.notNull(resourceId, "resourceId不能为空");
    // 默认count只添加一个
    count = null == count ? 1 : count;

    WebResource resource = baseDao.findOne(resourceId, WebResource.class);
    if (null == resource) {
      throw new IllegalStateException(String.format("没有找到对应的资源[id=%d]", resourceId));
    }

    OrderCart cart = new OrderCart();
    cart.setCustomerId(cusId);
    cart.setUserId(resource.getUserId());
    cart.setResourceId(resourceId);
    cart.setItemCount(count);
    cart.setUnitPrice(resource.getPrice());
    // cart.setCargoPrice(resource.get);运费
    cart.setTotalPrice(analyseTotal(cart));
    cart.setIseff(IsEff.EFFECTIVE.getValue());
    cart.setCreateTime(new Date());
    baseDao.save(cart);
  }
}
