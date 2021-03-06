package com.ewing.busi.order.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.order.constants.OrderStatus;
import com.ewing.busi.order.dao.OrderCartDao;
import com.ewing.busi.order.dto.AddCartReq;
import com.ewing.busi.order.dto.LightOrderCartReq;
import com.ewing.busi.order.dto.LightOrderCartResp;
import com.ewing.busi.order.dto.SubmitCartReq;
import com.ewing.busi.order.dto.SubmitCartReq.Item;
import com.ewing.busi.order.helper.OrderHelper;
import com.ewing.busi.order.model.OrderCart;
import com.ewing.busi.order.model.OrderDetail;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.busi.resource.model.WebResourcePrice;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.common.constants.IsEff;
import com.ewing.core.app.service.BaseModelService;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;
import com.ewing.utils.BizGenerator;
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
  public List<LightOrderCartResp> queryByCusId(Integer cusId, LightOrderCartReq req) {
    Validate.notNull(req, "入参不能为空");
    Integer page = req.getPage();
    Integer pageSize = req.getPageSize();
    Validate.notNull(cusId, "客户id不能为空");
    Validate.notNull(page, "page不能为空");
    Validate.notNull(pageSize, "pageSize不能为空");

    return orderCartDao.queryByCusId(cusId, page, pageSize);
  }

  /**
   * 进行购物车结算，返回订单id
   * 
   * @param cusId 客户id
   */
  public Integer balanceCart(Integer cusId, SubmitCartReq req){
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
    orderInfo.setCustomerId(cusId);
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

      OrderDetail detail =
          OrderHelper.initOrderDetail(cart, bizId, item.getItemCount(), orderInfo.getId());
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
   * @param priceId 如果没有priceId，则价格取web_resouce中的价格
   * @param count 购物车数量
   * @author Joeson
   * @param count2
   */
  public void addCart(Integer cusId, AddCartReq req) throws Exception {
    Validate.notNull(req, "入参不能为空");
    Validate.notNull(cusId, "cusId不能为空");

    Integer count = req.getCount();
    Integer resourceId = req.getResourceId();
    Integer priceId = req.getPriceId();
    Validate.notNull(resourceId, "resourceId不能为空");
    // 默认count只添加一个
    count = null == count ? 1 : count;

    WebResource resource = baseDao.findOne(resourceId, WebResource.class);
    if (null == resource) {
      throw new Exception(String.format("没有找到对应的资源[id=%d]", resourceId));
    }
    WebResourcePrice price =
        null == priceId ? null : baseDao.findOne(priceId, WebResourcePrice.class);

    OrderCart cart = orderCartDao.findByCusIdAndResIdAndPriId(cusId, resourceId, priceId);
    boolean isExist = null != cart; // 是否存在
    cart = null == cart ? new OrderCart() : cart;

    cart = new OrderCart();
    cart.setCustomerId(cusId);
    cart.setUserId(resource.getUserId());
    cart.setResourceId(resourceId);
    cart.setPriceId(priceId);
    cart.setItemCount(count);
    cart.setUnitPrice(null == price ? resource.getPrice() : price.getPrice());
    // cart.setCargoPrice(resource.get);运费 @TODO确认运费问题
    cart.setTotalPrice(analyseTotal(cart));
    cart.setIseff(IsEff.EFFECTIVE.getValue());
    cart.setCreateTime(new Date());

    if (isExist) {
      baseDao.update(cart);
    } else {
      baseDao.save(cart);
    }
  }
}
