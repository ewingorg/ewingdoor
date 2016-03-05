package com.ewing.busi.order.constants;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 客户订单状态<br/>
 * 
 * INIT ==提交订单==> WAIT_PAY ==支付==> WAIT_SEND ==发货，填写快递号==> WAIT_RECEIVE ==收货==> FINISHED
 * @author Joeson
 */
public enum OrderStatus {

    WAIT_PAY("0", "待付款"),

    WAIT_SEND("1", "待发货"),

    WAIT_RECEIVE("2", "待收货"),
    
    REFUND("3", "退款中"),
    
    FINISHED("4", "已完成"),
    
    CLOSED("5", "已关闭"),
    
    INIT("6", "初始化");

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
