package com.ewing.busi.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.collect.action.CustomerCollectAction;
import com.ewing.busi.web.dto.WebTemplateKeyReq;
import com.ewing.busi.web.dto.WebTemplateKeyResp;
import com.ewing.busi.web.service.WebBlockService;
import com.ewing.core.app.action.base.BaseAction;

/**
 * WEB模板属性配置
 * 
 * @author tansonlam
 * @createDate 2016年3月3日
 * 
 */
public class WebBlockAction extends BaseAction {

    private static Logger logger = Logger.getLogger(CustomerCollectAction.class);

    @Resource
    private WebBlockService webBlockService;

    /**
     * 查找商店使用的模板页面配置的分组属性
     */
    public void templateKey() {
        try {
            WebTemplateKeyReq req = getParamJson(WebTemplateKeyReq.class);
            checkRequired(req.getShopId(), "shopId");
            checkRequired(req.getTemplateName(), "templateName");
            List<WebTemplateKeyResp> keyList = webBlockService.listTemplateBlock(req.getShopId(),
                    req.getTemplateName());
            outSucResult(keyList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常");
        }
    }
}
