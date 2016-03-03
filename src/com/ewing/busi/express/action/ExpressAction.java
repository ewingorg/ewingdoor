package com.ewing.busi.express.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.express.dto.ExpressReq;
import com.ewing.busi.express.service.ExpressService;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.express.vo.ExpressRespDto;

/**
 * 快递的查询接口
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月21日
 *
 */
public class ExpressAction extends BaseAction{
  
  private static Logger logger = Logger.getLogger(ExpressAction.class);

  @Resource
  private ExpressService expressService;
  
  /**
   * 查找信息
   * @param num 快递单号
   * @param com 公司代号
   * 
   * @author Joeson
   */
  public void query() {
      try {
          ExpressReq req = getParamJson(ExpressReq.class);
          checkRequired(req.getCom(), "com");
          checkRequired(req.getNum(), "num");
          
          ExpressRespDto resp = expressService.query4Order(req.getCom(), req.getNum());
          outSucResult(resp);
      } catch (Exception e) {
          logger.error(e.getMessage(), e);
          outFailResult("内部异常");
      }
  } 

}
