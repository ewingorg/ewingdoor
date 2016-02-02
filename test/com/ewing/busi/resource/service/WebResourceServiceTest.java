package com.ewing.busi.resource.service;

import org.junit.Test;

import com.ewing.busi.resource.dto.ProductDetailResp;
import com.ewing.core.factory.SpringCtx;

/**
 * 类/接口注释
 * 
 * @author tansonlam
 * @createDate 2016年2月2日
 * 
 */
public class WebResourceServiceTest {
    @Test
    public void getProductDetail() throws Exception {
        try {
            WebResourceService webResourceService = (WebResourceService) SpringCtx
                    .getByBeanName("webResourceService");
            ProductDetailResp resp = webResourceService.getProductDetail(8);
            System.out.println(resp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
