package com.ewing.busi.order.constants;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 客户订单状态
 */
public enum OrderStatus {

    WAIT_PAY('0', "待付款"),

    WAIT_SEND('1', "待发货"),

    WAIT_RECEIVE('2', "待收货"),

    RECEIVEED('3', "已收货");

    private char value;
    private String msg;

    OrderStatus(char value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public char getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsg(char value) {
        if (!CharUtils.isAsciiNumeric(value)) {
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
