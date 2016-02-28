package com.ewing.busi.resource.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.resource.dto.LightCategoryResp;
import com.ewing.busi.resource.dto.LightProductInfoReq;
import com.ewing.busi.resource.dto.LightProductInfoResp;
import com.ewing.busi.resource.dto.ProductDetailReq;
import com.ewing.busi.resource.dto.ProductDetailResp;
import com.ewing.busi.resource.service.WebCategoryService;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.common.constants.IsHot;
import com.ewing.core.app.action.base.BaseAction;

/**
 * 产品控制类，提供产品相关数据
 * 
 * @author tangzz@ucweb.com
 * @createDate 2016年1月25日
 */

public class WebResourceAction extends BaseAction {
    private static Logger logger = Logger.getLogger(WebResourceAction.class);
    @Resource
    private WebResourceService webResourceService;
    @Resource
    private WebCategoryService webCategoryService;

    /**
     * 获取首页产品列表
     */
    // @CustomerLoginFilter
    public void queryIndexProduct() {

        try {
            LightProductInfoReq request = getParamJson(LightProductInfoReq.class);
            Integer isHot = request.getIsHot();
            Integer page = request.getPage();
            Integer pageSize = request.getPageSize();
            checkRequired(isHot, "isHot");
            checkRequired(page, "page");
            checkRequired(pageSize, "pageSize");

            List<LightProductInfoResp> list = webResourceService.pageQueryHotResource(
                getLoginCusId(), IsHot.fromValue(isHot).getValue(), page, pageSize);
            List<LightCategoryResp> categoryList = webCategoryService.queryByUserId(getUserId());
            
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("list", list);
            map.put("categoryList", categoryList);
            outSucResult(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    /**
     * 查找单个资源信息
     */
    public void getProductDetail() {
        try {
            ProductDetailReq request = getParamJson(ProductDetailReq.class);
            Integer pId = request.getpId();
            ProductDetailResp productDetailResp = webResourceService.getProductDetail(getLoginCusId(), pId);
            outSucResult(productDetailResp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
