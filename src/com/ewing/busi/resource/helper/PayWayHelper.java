package com.ewing.busi.resource.helper;

import java.util.List;

import com.ewing.busi.order.constants.PayWay;
import com.google.common.collect.Lists;

/**
 * 付费方式辅助类
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年3月3日
 */
public class PayWayHelper {
  
  private static List<Item> list = null;
  
  static{
    list = Lists.newArrayList();
    //@TODO 确定是否是否需要做成配置，还是后期优化
    list.add(new Item(PayWay.PAY_ONLINE.getValue(), PayWay.PAY_ONLINE.getMsg()));
  }
  
  public static List<Item> list(){
    return list;
  }
  
  public static class Item{
    private Integer value;
    
    private String msg;

    public Integer getValue() {
      return value;
    }

    public void setValue(Integer value) {
      this.value = value;
    }

    public String getMsg() {
      return msg;
    }

    public void setMsg(String msg) {
      this.msg = msg;
    }

    public Item(Integer value, String msg) {
      super();
      this.value = value;
      this.msg = msg;
    }
  }
}
