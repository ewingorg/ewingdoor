package com.ewing.busi.express.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.express.dto.ExpressReqDto;
import com.ewing.busi.express.dto.ExpressRespDto;
import com.ewing.busi.express.service.ExpressService;
import com.ewing.core.app.action.base.BaseAction;

/**
 * 快递的查询接口
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月21日
 * 
 */
public class ExpressAction extends BaseAction {

  private static Logger logger = Logger.getLogger(ExpressAction.class);

  @Resource
  private ExpressService expressService;

  /**
   * 查找信息
   * 
   * @param num 快递单号
   * @param com 公司代号
   * 
   * @author Joeson
   */
  public void query() {
    try {
      ExpressReqDto req = getParamJson(ExpressReqDto.class);
      checkRequired(req.getOrderId(), "orderId");
      checkRequired(req.getExpressCom(), "expressNum");
      checkRequired(req.getExpressNum(), "expressNum");

      ExpressRespDto resp =
          expressService.query4Order(getLoginCusId(), req.getOrderId(), req.getExpressCom(),
              req.getExpressNum());
      outSucResult(resp);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      outFailResult("内部异常");
    }
  }

}
