package com.ewing.busi.customer.constants;

/**
 * 客户订单状态
 */
public enum AddressDefault {

    UN_DEFAULT("0", "不是默认"),

    DEFAULT("1", "默认");

    private String value;
    private String msg;

    AddressDefault(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}
