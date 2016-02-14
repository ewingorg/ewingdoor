package com.ewing.busi.order.constants;

/**
 * 客户订单状态
 */
public enum PayWay {

    
    PAY_ONLINE(0, "在线付款"),

    PAY_AFTER_RECEIVE(1, "货到付款");
    

    private int value;
    private String msg;

    PayWay(int value, String msg) {
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
