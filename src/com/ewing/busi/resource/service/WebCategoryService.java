package com.ewing.busi.resource.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.dao.WebCategoryDao;
import com.ewing.busi.resource.dto.CategoryResp;
import com.ewing.busi.resource.model.WebCategory;
import com.ewing.core.redis.RedisCache;
import com.ewing.utils.BeanCopy;
import com.ewing.utils.IntegerUtils;

@Repository("webCategoryService")
public class WebCategoryService {

    @Resource
    private WebCategoryDao webCategoryDao;

    /**
     * 查找某个商店的分类
     * 
     * @param userId
     * @return
     */
    @RedisCache(key = "queryShopCategory", keyParamNames = { "userId" })
    public List<CategoryResp> queryShopCategory(Integer userId) {
        if (IntegerUtils.nullOrZero(userId)) {
            return Collections.EMPTY_LIST;
        } 
        List<WebCategory> list = webCategoryDao.findByUserId(userId);
        List<CategoryResp> dtoList = BeanCopy.copy(list, CategoryResp.class);
        return dtoList;
    }

}
