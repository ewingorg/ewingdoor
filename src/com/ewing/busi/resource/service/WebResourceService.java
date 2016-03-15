package com.ewing.busi.resource.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.collect.model.CustomerCollect;
import com.ewing.busi.collect.service.CustomerCollectService;
import com.ewing.busi.resource.dao.WebResourceDao;
import com.ewing.busi.resource.dao.WebResourcePriceDao;
import com.ewing.busi.resource.dao.WebResourceSpecDao;
import com.ewing.busi.resource.dto.CategoryProductInfoReq;
import com.ewing.busi.resource.dto.CategoryProductInfoResp;
import com.ewing.busi.resource.dto.LightProductInfoReq;
import com.ewing.busi.resource.dto.LightProductInfoResp;
import com.ewing.busi.resource.dto.ProductDetailDto;
import com.ewing.busi.resource.dto.ProductDetailResp;
import com.ewing.busi.resource.dto.ProductPriceDto;
import com.ewing.busi.resource.dto.ProductSpecGroup;
import com.ewing.busi.resource.dto.WebResourceDto;
import com.ewing.busi.resource.model.WebCategory;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.common.constants.IsHot;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.redis.RedisCache;
import com.ewing.utils.FileUrlUtil;
import com.ewing.utils.IntegerUtils;
import com.google.common.collect.Lists;

/**
 * 资源服务类
 * 
 * @author tansonlam
 * @createDate 2016年1月26日
 * 
 */
@Repository("webResourceService")
public class WebResourceService extends BaseService {
  @Resource
  public BaseDao baseDao;
  @Resource
  private WebResourceDao webResourceDao;
  @Resource
  private WebResourcePriceDao webResourcePriceDao;
  @Resource
  private WebResourceSpecDao webResourceSpecDao;
  @Resource
  private WebResourceSpecService webResourceSpecService;
  @Resource
  private WebResourcePriceService webResourcePriceService;
  @Resource
  private CustomerCollectService customerCollectService;
  @Resource
  private WebCategoryService webCategoryService;
  
  /**
   * 获取资源信息，支持分页获取数据
   * 
   * @param shopId 商铺ID
   * @param isHot 是否热门资源
   * @param page
   * @param pageSize
   * 
   * @return
   */
  @RedisCache(key = "lightProductList", keyParamNames = {"shopId", "isHot"}, isList = true)
  public List<LightProductInfoResp> pageIndexResource(LightProductInfoReq req) {
    Integer isHot = req.getIsHot();
    Integer shopId = req.getShopId();
    Integer page = req.getPage();
    Integer pageSize = req.getPageSize();
    Validate.notNull(shopId, "shopId不能为空");
    Validate.notNull(page, "page不能为空");
    Validate.notNull(pageSize, "pageSize不能为空");

    List<LightProductInfoResp> dtoList = new ArrayList<LightProductInfoResp>();
    List<WebResource> list = webResourceDao.pageQueryHotResource(shopId, isHot, page, pageSize);
    for (WebResource webResource : list) {
      LightProductInfoResp lightProductInfo = new LightProductInfoResp();
      try {
        BeanUtils.copyProperties(lightProductInfo, webResource);
        lightProductInfo
            .setImageUrl(FileUrlUtil.convertResourceUrl(lightProductInfo.getImageUrl()));
        dtoList.add(lightProductInfo);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return dtoList;
  }

  /**
   * 查找单个资源信息
   * 
   * @param resourceId
   * @return
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  @RedisCache(key = "productDetail", keyParamNames = {"resourceId"})
  public ProductDetailResp getProductDetail(Integer cusId, Integer resourceId) {
    WebResource webresource = webResourceDao.findOne(resourceId);
    if (webresource == null)
      return null;

    CustomerCollect collect = customerCollectService.findByCusIdAndResId(cusId, resourceId);
    ProductDetailResp detailResponse = new ProductDetailResp();
    ProductDetailDto productDetail = new ProductDetailDto();
    // 获取属性列表
    List<ProductSpecGroup> specList = webResourceSpecService.getConfigureSpecs(resourceId);
    List<ProductPriceDto> priceList = webResourcePriceService.findByResourceId(resourceId);
    productDetail.setPriceRange(webResourcePriceService.getPriceRange(priceList));// 设置价格范围
    productDetail.setIsCollect(null != collect ? 1 : 0);
    try {
      BeanUtils.copyProperties(productDetail, webresource);
      productDetail.setImageUrl(FileUrlUtil.convertResourceUrl(productDetail.getImageUrl()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    productDetail.setIsCollect(null != collect ? 1 : 0);
    detailResponse.setProductDetail(productDetail);
    detailResponse.setPriceList(priceList);
    detailResponse.setSpecList(specList);

    return detailResponse;
  }

  /**
   * 根据categoryId查找某个商店的
   * 
   * @param shopId
   * @param categoryId
   * @param page
   * @param pageSize
   * @return
   * @author Joeson
   */
  public List<CategoryProductInfoResp> queryByCategory(CategoryProductInfoReq req) {
    Integer shopId = req.getShopId();
    Integer categoryId = req.getCategoryId();
    Integer page = req.getPage();
    Integer pageSize = req.getPageSize();
    Validate.notNull(shopId, "shopId不能为空");
    Validate.notNull(page, "page不能为空");
    Validate.notNull(pageSize, "pageSize不能为空");

    List<CategoryProductInfoResp> dtoList = new ArrayList<CategoryProductInfoResp>();
    List<WebResourceDto> list = webResourceDao.queryByCategory(shopId, categoryId, page, pageSize);
    for (WebResourceDto webResource : list) {
      CategoryProductInfoResp lightProductInfo = new CategoryProductInfoResp();
      try {
        BeanUtils.copyProperties(lightProductInfo, webResource);
        lightProductInfo
            .setImageUrl(FileUrlUtil.convertResourceUrl(lightProductInfo.getImageUrl()));
        dtoList.add(lightProductInfo);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    return dtoList;
  }

}
