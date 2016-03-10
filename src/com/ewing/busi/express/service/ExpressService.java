package com.ewing.busi.express.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import com.ewing.busi.express.dto.ExpressRespDto;
import com.ewing.busi.order.dao.OrderInfoDao;
import com.ewing.busi.order.helper.OrderHelper;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.busi.order.service.OrderDetailService;
import com.ewing.busi.order.service.OrderInfoService;
import com.ewing.busi.sysparam.model.SysParam;
import com.ewing.busi.sysparam.service.SysParamService;
import com.ewing.core.express.service.ExpressApiService;
import com.ewing.core.express.vo.ExpressApiRespDto;
import com.ewing.core.jdbc.BaseDao;

@Repository("expressService")
public class ExpressService {
  @Resource
  private ExpressApiService expressApiService;
  @Resource
  private OrderInfoDao orderInfoDao;
  @Resource
  private BaseDao baseDao;
  @Resource
  private SysParamService sysParamService;
  /**
   * 
   * @param cusId
   * @param orderId
   * @param expressCom
   * @param expressNum 快递单号不能为空
   * @return
   * @author Joeson
   */
  public ExpressRespDto query4Order(Integer cusId, Integer orderId, String expressCom,
      String expressNum) {
    Validate.notNull(cusId, "商家id不能为空");
    Validate.notNull(orderId, "订单id不能为空");
    Validate.notNull(expressCom, "快递公司名称不能为空");
    Validate.notNull(expressNum, "快递单号不能为空");

    OrderInfo order = orderInfoDao.findByIdAndCusId(orderId, cusId);
    //@TODO定义一个contants
    SysParam sysParam = sysParamService.findByRootCodeAndParamName("CARGO_LIST", expressCom);
    String com = null != sysParam ? sysParam.getParamValue() : null;
    return new ExpressRespDto(OrderHelper.toAddress(order), expressApiService.request(com,
        expressNum));
  }



}
