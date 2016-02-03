package com.ewing.busi.resource.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.dao.WebResourceDao;
import com.ewing.busi.resource.dao.WebResourcePriceDao;
import com.ewing.busi.resource.dao.WebResourceSpecDao;
import com.ewing.busi.resource.dto.ProductSpec;
import com.ewing.busi.resource.dto.ProductSpecGroup;
import com.ewing.busi.resource.model.WebResourceSpec;
import com.ewing.busi.resource.util.ProductSpecComparatorUtil;

/**
 * 资源规格服务类
 * 
 * @author tansonlam
 * @createDate 2016年1月27日
 * 
 */
@Repository("webResourceSpecService")
public class WebResourceSpecService {

    @Resource
    private WebResourceDao webResourceDao;
    @Resource
    private WebResourcePriceDao webResourcePriceDao;
    @Resource
    private WebResourceSpecDao webResourceSpecDao;

    /**
     * 获取配置的资源規格,以规格组的结构返回
     * 
     * @param resourceId
     * @return
     */
    public List<ProductSpecGroup> getConfigureSpecs(Integer resourceId) {
        List<ProductSpecGroup> groupList = new ArrayList<ProductSpecGroup>();
        List<WebResourceSpec> specList = webResourceSpecDao.getConfigureSpecs(resourceId);
        for (WebResourceSpec spec : specList) {
            if (!StringUtils.isEmpty(spec.getRootSpec()))
                continue;
            ProductSpec rootSpecDto = new ProductSpec();
            try {
                BeanUtils.copyProperties(rootSpecDto, spec);

                ProductSpecGroup group = new ProductSpecGroup();
                group.setRootSpec(rootSpecDto);
                for (WebResourceSpec childSpec : specList) {
                    if (!StringUtils.isEmpty(childSpec.getRootSpec())
                            && childSpec.getRootSpec().equals(spec.getSpec())) {
                        ProductSpec childSpecDto = new ProductSpec();
                        BeanUtils.copyProperties(childSpecDto, childSpec);
                        group.addChildSpec(childSpecDto);
                    }
                }
                groupList.add(group);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        ProductSpecComparatorUtil.sortResGroupList(groupList);
        return groupList;
    }
}
