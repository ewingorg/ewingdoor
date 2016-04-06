package com.ewing.busi.order.constants;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 处理历史类型<br/>
 * 
 * @author Joeson
 */
public enum ProcessHistoryType {

    ORDER(1, "订单"),

    REFUND(2, "退款");

    private Integer value;
    private String msg;

    ProcessHistoryType(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public Integer getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsg(Integer value) {
        if (null == value) {
            return StringUtils.EMPTY;
        }

        for (ProcessHistoryType v : ProcessHistoryType.values()) {
            if (ObjectUtils.equals(v.getValue(), value)) {
                return v.getMsg();
            }
        }

        return StringUtils.EMPTY;
    }
}
