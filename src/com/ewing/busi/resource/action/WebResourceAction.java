package com.ewing.busi.resource.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ewing.busi.resource.dto.CategoryProductInfoReq;
import com.ewing.busi.resource.dto.CategoryProductInfoResp;
import com.ewing.busi.resource.dto.CategoryReq;
import com.ewing.busi.resource.dto.CategoryResp;
import com.ewing.busi.resource.dto.LightProductInfoReq;
import com.ewing.busi.resource.dto.LightProductInfoResp;
import com.ewing.busi.resource.dto.ProductDetailReq;
import com.ewing.busi.resource.dto.ProductDetailResp;
import com.ewing.busi.resource.model.WebCategory;
import com.ewing.busi.resource.service.WebCategoryService;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.busi.seller.service.SellerShopService;
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
    @Resource
    private SellerShopService sellerShopService;

    /**
     * 获取首页产品列表
     */
    public void queryIndexProduct() {

        try {
            LightProductInfoReq req = getParamJson(LightProductInfoReq.class);
            List<LightProductInfoResp> list = webResourceService.pageIndexResource(req);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", list);
            map.put("shopName", sellerShopService.findName(req.getShopId()));
            outSucResult(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    /**
     * 分类列表
     * 
     * @author Joeson
     */
    public void queryByCategory(){
      try {
        CategoryProductInfoReq req = getParamJson(CategoryProductInfoReq.class);
          checkRequired(req, "入参不能为空");
          
          List<CategoryProductInfoResp> list = webResourceService.queryByCategory(req);
          WebCategory category = webCategoryService.find(req.getCategoryId());
          Map<String,Object> map = new HashMap<String, Object>();
          map.put("list", list);
          map.put("category", null != category ? category.getName() : StringUtils.EMPTY);
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
            List<CategoryResp> list = webCategoryService.queryShopCategory(req.getShopId());
            outSucResult(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
