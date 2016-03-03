package com.ewing.busi.express.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.collect.action.CustomerCollectAction;
import com.ewing.busi.collect.dto.LightCollectReq;
import com.ewing.busi.collect.dto.LightCollectResp;
import com.ewing.busi.collect.service.CustomerCollectService;
import com.ewing.busi.express.dto.ExpressReq;
import com.ewing.busi.express.dto.ExpressResp;
import com.ewing.busi.express.service.ExpressService;
import com.ewing.core.app.action.base.BaseAction;

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
  public void list() {
      try {
//          ExpressReq req = getParamJson(ExpressReq.class);
//          checkRequired(req.getCom(), "com");
//          checkRequired(req.getNum(), "num");
//          
//          List<ExpressResp> list = customerCollectService.findByCusId(getLoginCusId(), req.getPage(), req.getPageSize());
//          Map<String, Object> map = new HashMap<String, Object>();
//          map.put("list", list);
//          outSucResult(map);
      } catch (Exception e) {
          logger.error(e.getMessage(), e);
          outFailResult("内部异常");
      }
  } 

}
