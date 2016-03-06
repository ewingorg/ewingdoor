package com.ewing.busi.collect.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.collect.dto.AddCollectReq;
import com.ewing.busi.collect.dto.DelCollectReq;
import com.ewing.busi.collect.dto.LightCollectReq;
import com.ewing.busi.collect.dto.LightCollectResp;
import com.ewing.busi.collect.service.CustomerCollectService;
import com.ewing.common.constants.AjaxRespCode;
import com.ewing.core.app.action.base.BaseAction;

/**
 * 
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月23日
 */
public class CustomerCollectAction extends BaseAction{
    
    private static Logger logger = Logger.getLogger(CustomerCollectAction.class);

    @Resource
    private CustomerCollectService customerCollectService;
    
    /**
     * 查找信息
     * 
     * @author Joeson
     */
    public void queryIndex() {
        try {
            LightCollectReq req = getParamJson(LightCollectReq.class);
            checkRequired(req.getPage(), "page");
            checkRequired(req.getPageSize(), "pageSize");
            
            List<LightCollectResp> list = customerCollectService.findByCusId(getLoginCusId(), req.getPage(), req.getPageSize());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", list);
            outSucResult(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    } 
    
    /**
     * 添加收件夹
     * 
     * @author Joeson
     */
    public void addCollect(){
        try {
            AddCollectReq req = getParamJson(AddCollectReq.class);
            checkRequired(req.getResId(), "resource_id");
            
            boolean result = customerCollectService.addCollect(getLoginCusId(), req.getResId());
            if(result){
              outSucResult(AjaxRespCode.CODE_SUC.code);
            }else{
              outFailResult(AjaxRespCode.CODE_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult(AjaxRespCode.CODE_ERROR);
        }
    }
    
    /**
     * 添加收件夹
     * 
     * @author Joeson
     */
    public void delCollect(){
        try {
            DelCollectReq req = getParamJson(DelCollectReq.class);
            checkRequired(req.getResId(), "resource_id");
            
            boolean result = customerCollectService.delCollect(getLoginCusId(), req.getResId());
            if(result)
              {
              outSucResult(AjaxRespCode.CODE_SUC.code);
              }else{
                outFailResult(AjaxRespCode.CODE_ERROR);
              }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult(AjaxRespCode.CODE_ERROR);
        }
    }

}
