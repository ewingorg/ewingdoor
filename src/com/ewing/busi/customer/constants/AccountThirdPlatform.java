package com.ewing.busi.customer.constants;

/**
 * 第三方账户平台
 */
public enum AccountThirdPlatform {

    WE_CHAT(1, "微信"),

    QQ(2, "淘宝");

    private int value;
    private String msg;

    AccountThirdPlatform(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}
