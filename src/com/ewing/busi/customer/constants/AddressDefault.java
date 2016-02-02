package com.ewing.busi.customer.constants;

/**
 * 客户订单状态
 */
public enum AddressDefault {

    UN_DEFAULT('0', "不是默认"),

    DEFAULT('1', "默认");

    private char value;
    private String msg;

    AddressDefault(char value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public char getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}
