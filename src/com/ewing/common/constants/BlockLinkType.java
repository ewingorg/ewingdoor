package com.ewing.common.constants;

import org.apache.axis.utils.StringUtils;

import com.ewing.utils.FileUrlUtil;

/**
 * 链接类型
 */
public enum BlockLinkType {

    RESOURCE("0", "资源地址"),

    SHOP("1", "商店URL"),

    OUTER("2", "站外资源");

    private String value;
    private String msg;

    BlockLinkType(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

    public static String convertUrl(String linkType, String relativePath) {
        if (StringUtils.isEmpty(linkType) || StringUtils.isEmpty(relativePath)) {
            return null;
        }
        if (BlockLinkType.OUTER.getValue().equals(linkType)) {
            return relativePath;
        }
        if (BlockLinkType.RESOURCE.getValue().equals(linkType)) {
            return FileUrlUtil.convertResourceUrl(relativePath);
        }
        if (BlockLinkType.SHOP.getValue().equals(linkType)) {
            return FileUrlUtil.convertShopUrl(relativePath);
        }
        return null;
    }
}
