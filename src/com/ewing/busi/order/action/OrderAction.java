package com.ewing.busi.order.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.order.service.OrderCartService;
import com.ewing.busi.resource.action.WebResourceAction;
import com.ewing.busi.resource.dto.LightProductInfoReq;
import com.ewing.busi.resource.dto.LightProductInfoResp;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.common.constants.IsHot;
import com.ewing.core.app.action.base.BaseAction;

/**
 * 订单的相关操作
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年2月2日
 *
 */
public class OrderAction extends BaseAction{
    
    private static Logger logger = Logger.getLogger(WebResourceAction.class);
    @Resource
    private OrderCartService orderCartService;

    /**
     * 获取首页产品列表
     */
    public void queryIndexProduct() {

        try {
//            LightProductInfoReq request = getParamJson(LightProductInfoReq.class);
//            Integer isHot = request.getIsHot();
//            Integer page = request.getPage();
//            Integer pageSize = request.getPageSize();
//            Integer userId = request.getUserId();
//            checkRequired(isHot, "isHot");
//            checkRequired(page, "page");
//            checkRequired(pageSize, "pageSize");
//            checkRequired(userId, "userId");
//
//            List<LightProductInfoResp> list = webResourceService.pageQueryHotResource(userId, IsHot
//                    .fromValue(isHot).getValue(), page, pageSize);
//            outSucResult(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    

}
