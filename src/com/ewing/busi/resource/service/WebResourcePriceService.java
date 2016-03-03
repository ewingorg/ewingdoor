package com.ewing.busi.resource.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.dao.WebResourceDao;
import com.ewing.busi.resource.dao.WebResourcePriceDao;
import com.ewing.busi.resource.dto.ProductPriceDto;
import com.ewing.busi.resource.model.WebResourcePrice;
import com.ewing.utils.FloatUtils;

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
  public List<ProductPriceDto> findByResourceId(Integer resourceId) {
    List<WebResourcePrice> list = webResourcePriceDao.findByResourceId(resourceId);
    List<ProductPriceDto> dtoList = new ArrayList<ProductPriceDto>();

    for (WebResourcePrice model : list) {
      ProductPriceDto dto = new ProductPriceDto();
      try {
        BeanUtils.copyProperties(dto, model);
        dtoList.add(dto);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    return dtoList;
  }

  /**
   * 获取价格区间(eg:两个50-100，一个50)
   * 
   * @return
   * @author Joeson
   */
  public String getPriceRange(List<ProductPriceDto> dtoList) {
    if (CollectionUtils.isEmpty(dtoList)) {
      return StringUtils.EMPTY;
    }

    Float minPrice = Float.MAX_VALUE;
    Float maxPrice = Float.MIN_VALUE;

    for (ProductPriceDto dto : dtoList) {
      if (FloatUtils.isGt(minPrice, dto.getPrice())) {
        minPrice = dto.getPrice();
      }
      if (FloatUtils.isLt(maxPrice, dto.getPrice())) {
        maxPrice = dto.getPrice();
      }
    }

    if (FloatUtils.isEqual(minPrice, maxPrice)) {
      return minPrice.toString();
    } else {
      return minPrice + "-" + maxPrice;
    }
  }

  public static void main(String[] args) {

    System.out.println(Float.MIN_VALUE);
  }


}
