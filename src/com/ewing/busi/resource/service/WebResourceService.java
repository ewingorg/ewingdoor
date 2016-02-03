package com.ewing.busi.resource.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

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

/**
 * 资源服务类
 * 
 * @author tansonlam
 * @createDate 2016年1月26日
 * 
 */
@Repository("webResourceService")
public class WebResourceService {
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
    public ProductDetailResp getProductDetail(Integer resourceId) {
        WebResource webresource = webResourceDao.findOne(resourceId);
        if (webresource == null)
            return null;
        ProductDetailResp detailResponse = new ProductDetailResp();
        ProductDetailDto productDetail = new ProductDetailDto();
        List<ProductSpecGroup> specList = webResourceSpecService.getConfigureSpecs(resourceId);
        List<ProductPriceDto> priceList = webResourcePriceService.findByResourceId(resourceId);
        try {
            BeanUtils.copyProperties(productDetail, webresource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        detailResponse.setProductDetail(productDetail);
        detailResponse.setPriceList(priceList);
        detailResponse.setSpecList(specList);
        return detailResponse;
    }

}
