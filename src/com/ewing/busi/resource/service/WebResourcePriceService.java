package com.ewing.busi.resource.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.dao.WebResourceDao;
import com.ewing.busi.resource.dao.WebResourcePriceDao;
import com.ewing.busi.resource.dto.ProductPriceDto;
import com.ewing.busi.resource.model.WebResourcePrice;

/**
 * 资源价格服务类
 * 
 * @author tansonlam
 * @createDate 2016年1月27日
 * 
 */
@Repository("webResourcePriceService")
public class WebResourcePriceService {
    @Resource
    private WebResourceDao webResourceDao;
    @Resource
    private WebResourcePriceDao webResourcePriceDao;

    /**
     * 查找指定资源的价格设置
     * 
     * @param resourceId
     * @return
     */
    public  List<ProductPriceDto> findByResourceId(Integer resourceId) {
        List<WebResourcePrice> list = webResourcePriceDao.findByResourceId(resourceId);
        List<ProductPriceDto> dtoList = new ArrayList<ProductPriceDto>();
        for (WebResourcePrice model : list) {
            ProductPriceDto dto = new ProductPriceDto();
            try {
                BeanUtils.copyProperties(dto, model);
                dtoList.add(dto);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return dtoList;
    }
}
