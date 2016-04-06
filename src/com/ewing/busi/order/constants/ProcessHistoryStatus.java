package com.ewing.busi.order.constants;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 订单处理状态<br/>
 * 
 * @author Joeson
 */
public enum ProcessHistoryStatus {

  RUNNING("0", "进行中"),

  DONE("1", "完成"),

  FAIL("2", "失败");

  private String value;
  private String msg;

  ProcessHistoryStatus(String value, String msg) {
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

    for (ProcessHistoryStatus v : ProcessHistoryStatus.values()) {
      if (ObjectUtils.equals(v.getValue(), value)) {
        return v.getMsg();
      }
    }

    return StringUtils.EMPTY;
  }
}
