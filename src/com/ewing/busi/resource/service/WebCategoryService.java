package com.ewing.busi.resource.service;


import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.dao.WebCategoryDao;
import com.ewing.busi.resource.dto.LightCategoryResp;
import com.ewing.busi.resource.model.WebCategory;
import com.ewing.utils.BeanCopy;
import com.ewing.utils.IntegerUtils;

@Repository
public class WebCategoryService {
  
  @Resource
  private WebCategoryDao webCategoryDao;

  /**
   * 根据userId查询
   * @param loginCusId
   * @return
   * @author Joeson
   */
  public List<LightCategoryResp> queryByUserId(Integer userId) {
    if(IntegerUtils.nullOrZero(userId)){
      return Collections.EMPTY_LIST;
    }
    
    List<WebCategory> list = webCategoryDao.findByUserId(userId);
    List<LightCategoryResp> dtoList = BeanCopy.copy(list, LightCategoryResp.class);
    return dtoList;
  }
  
  
}
