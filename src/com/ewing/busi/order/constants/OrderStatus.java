package com.ewing.busi.order.constants;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 客户订单状态
 */
public enum OrderStatus {

    WAIT_PAY("0", "待付款"),

    WAIT_SEND("1", "待发货"),

    WAIT_RECEIVE("2", "待收货"),

    RECEIVEED("3", "已收货");

    private String value;
    private String msg;

    OrderStatus(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsg(String value) {
        if (StringUtils.isEmpty(value)) {
            return StringUtils.EMPTY;
        }

        for (OrderStatus v : OrderStatus.values()) {
            if (ObjectUtils.equals(v.getValue(), value)) {
                return v.getMsg();
            }
        }

        return StringUtils.EMPTY;
    }
}
