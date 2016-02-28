package com.ewing.busi.resource.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.base.service.BaseService;
import com.ewing.busi.collect.model.CustomerCollect;
import com.ewing.busi.collect.service.CustomerCollectService;
import com.ewing.busi.resource.dao.WebResourceDao;
import com.ewing.busi.resource.dao.WebResourcePriceDao;
import com.ewing.busi.resource.dao.WebResourceSpecDao;
import com.ewing.busi.resource.dto.LightProductInfoResp;
import com.ewing.busi.resource.dto.ProductDetailDto;
import com.ewing.busi.resource.dto.ProductDetailResp;
import com.ewing.busi.resource.dto.ProductPriceDto;
import com.ewing.busi.resource.dto.ProductSpecGroup;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.redis.RedisCache;
import com.ewing.utils.FileUrlUtil;

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
    
    /**
     * 获取资源信息，支持分页获取数据
     * 
     * @param userId 用户ID
     * @param isHot 是否热门资源
     * @param page
     * @param pageSize
     * 
     * @return
     */
    @RedisCache(key = "lightProductList", keyParamNames = { "userId", "isHot" }, isList = true)
    public List<LightProductInfoResp> pageQueryHotResource(Integer userId, Integer isHot,
            Integer page, Integer pageSize) {

        List<LightProductInfoResp> dtoList = new ArrayList<LightProductInfoResp>();
        List<WebResource> list = webResourceDao.pageQueryHotResource(userId, isHot, page, pageSize);
        for (WebResource webResource : list) {
            LightProductInfoResp lightProductInfo = new LightProductInfoResp();
            try {
                BeanUtils.copyProperties(lightProductInfo, webResource);
                lightProductInfo.setImageUrl(FileUrlUtil.convertImgUrl(lightProductInfo
                        .getImageUrl()));
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
    @RedisCache(key = "productDetail", keyParamNames = { "resourceId" })
    public ProductDetailResp getProductDetail(Integer cusId, Integer resourceId) {
        WebResource webresource = webResourceDao.findOne(resourceId);
        if (webresource == null)
            return null;
        
        CustomerCollect collect = customerCollectService.findByCusIdAndResId(cusId, resourceId);
        ProductDetailResp detailResponse = new ProductDetailResp();
        ProductDetailDto productDetail = new ProductDetailDto();
        List<ProductSpecGroup> specList = webResourceSpecService.getConfigureSpecs(resourceId);
        List<ProductPriceDto> priceList = webResourcePriceService.findByResourceId(resourceId);
        try {
            BeanUtils.copyProperties(productDetail, webresource);
            productDetail.setImageUrl(FileUrlUtil.convertImgUrl(productDetail.getImageUrl()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        productDetail.setIsCollect(null != collect ? 1 : 0);
        detailResponse.setProductDetail(productDetail);
        detailResponse.setPriceList(priceList);
        detailResponse.setSpecList(specList);
        
        return detailResponse;
    }

}
