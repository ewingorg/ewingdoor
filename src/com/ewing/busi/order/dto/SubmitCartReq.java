package com.ewing.busi.order.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 提交购物车请求参数
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年2月6日
 *
 */
public class SubmitCartReq implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private List<Item> list;
    
    public class Item{
        private Integer id;

        private Integer itemCount;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getItemCount() {
            return itemCount;
        }

        public void setItemCount(Integer itemCount) {
            this.itemCount = itemCount;
        }
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }
    
    
    

}
