package com.ewing.busi.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.seller.dao.SellerShopDao;
import com.ewing.busi.seller.model.SellerShop;
import com.ewing.busi.web.dao.WebBlockDao;
import com.ewing.busi.web.dao.WebTemplateDao;
import com.ewing.busi.web.dao.WebTemplatePackageDao;
import com.ewing.busi.web.dto.WebBlockDto;
import com.ewing.busi.web.dto.WebTemplateKeyResp;
import com.ewing.busi.web.model.WebBlock;
import com.ewing.busi.web.model.WebTemplate;
import com.ewing.common.constants.BlockLinkType;
import com.ewing.core.redis.RedisCache;
import com.ewing.utils.BeanCopy;
import com.ewing.utils.FileUrlUtil;

/**
 * 类/接口注释
 * 
 * @author tansonlam
 * @createDate 2016年3月3日
 * 
 */
@Repository("webBlockService")
public class WebBlockService {
    @Resource
    private WebTemplatePackageDao webTemplatePackageDao;
    @Resource
    private WebTemplateDao WebTemplateDao;
    @Resource
    private WebBlockDao webBlockDao;
    @Resource
    private SellerShopDao sellerShopDao;

    /**
     * 查找商店使用的模板页面配置的分组属性
     * 
     * @param shopId
     * @param templateName
     * @return
     */
    @RedisCache(key = "listTemplateBlock", keyParamNames = { "shopId", "templateName" }, isList = false)
    public List<WebTemplateKeyResp> listTemplateBlock(Integer shopId, String templateName) {
        // 查找对应的商店
        SellerShop sellerShop = sellerShopDao.findShop(shopId);
        if (sellerShop == null || sellerShop.getTemplatePackageId() == null
                || sellerShop.getTemplatePackageId() == 0)
            return Collections.EMPTY_LIST;

        // 查找对应的模板
        WebTemplate webTemplate = WebTemplateDao.findByTemplateName(templateName,
                sellerShop.getTemplatePackageId());
        if (webTemplate == null || StringUtils.isEmpty(webTemplate.getGroupKeys()))
            return Collections.EMPTY_LIST;

        List<WebBlock> webBlockList = webBlockDao.listGroupBlock(shopId, webTemplate.getGroupKeys()
                .split(","));
        Map<String, List<WebBlock>> groupMap = new HashMap<String, List<WebBlock>>();
        for (WebBlock webBlock : webBlockList) {
            convertBlockUrl(webBlock);
            List<WebBlock> blockList = groupMap.get(webBlock.getGroupKey());
            if (blockList == null) {
                blockList = new ArrayList<WebBlock>();
                groupMap.put(webBlock.getGroupKey(), blockList);
            }
            blockList.add(webBlock);
        }

        List<WebTemplateKeyResp> groupDtoList = new ArrayList<WebTemplateKeyResp>();
        for (String groupKey : groupMap.keySet()) {
            WebTemplateKeyResp webGroupDto = new WebTemplateKeyResp();
            List<WebBlock> blockList = groupMap.get(groupKey);
            List<WebBlockDto> blockDtoList = BeanCopy.copy(blockList, WebBlockDto.class);
            webGroupDto.setGroupKey(groupKey);
            webGroupDto.setBlockList(blockDtoList);
            groupDtoList.add(webGroupDto);
        }
        return groupDtoList;
    }

    /**
     * 转换路径
     * 
     * @param webBlock
     */
    private void convertBlockUrl(WebBlock webBlock) {
        webBlock.setImageUrl(FileUrlUtil.convertResourceUrl(webBlock.getImageUrl()));
        webBlock.setLinkUrl(BlockLinkType.convertUrl(webBlock.getLinkType(), webBlock.getLinkUrl()));
    }

}
