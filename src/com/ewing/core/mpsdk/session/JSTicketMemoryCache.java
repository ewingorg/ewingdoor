package com.ewing.core.mpsdk.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ewing.core.mpsdk.vo.api.JSTicket;
import com.ewing.core.wxpaysdk.vo.cache.MemoryCache;

/**
 * 本地缓存JSTicket信息
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class JSTicketMemoryCache implements MemoryCache<JSTicket> {

    private Map<String, JSTicket> jsts = new ConcurrentHashMap<String, JSTicket>();

    @Override
    public JSTicket get(String key) {
        return jsts.get(key);
    }

    @Override
    public void set(String key, JSTicket jsTicket) {
        jsts.put(key, jsTicket);
    }

    @Override
    public void remove(String key) {
        jsts.remove(key);
    }

}
