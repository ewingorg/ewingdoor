package com.ewing.busi.resource.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.resource.dto.CategoryReq;
import com.ewing.busi.resource.dto.CategoryResp;
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
 * @author tanlam
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
    public void queryIndexProduct() {

        try {
            LightProductInfoReq req = getParamJson(LightProductInfoReq.class);
            Integer isHot = req.getIsHot();
            Integer page = req.getPage();
            Integer pageSize = req.getPageSize();
            Integer userId = req.getUserId();
            checkRequired(isHot, "isHot");
            checkRequired(page, "page");
            checkRequired(pageSize, "pageSize");
            List<LightProductInfoResp> list = webResourceService.pageQueryHotResource(userId, IsHot
                    .fromValue(isHot).getValue(), page, pageSize);
            outSucResult(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    /**
     * 查找单个资源信息
     */
    public void getProductDetail() {
        try {
            ProductDetailReq req = getParamJson(ProductDetailReq.class);
            Integer pId = req.getpId();
            ProductDetailResp productDetailResp = webResourceService.getProductDetail(
                    getLoginCusId(), pId);
            outSucResult(productDetailResp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 查询商店分类
     */ 
    public void getProductCategory() {
        try {
            CategoryReq req = getParamJson(CategoryReq.class);
            List<CategoryResp> list = webCategoryService.queryShopCategory(req.getUserId());
            outSucResult(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
