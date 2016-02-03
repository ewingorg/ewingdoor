package com.ewing.core.redis;

/**
 * redis异常类
 * 
 * @author tansonlam
 * @createDate 2016年2月2日
 * 
 */
public class RedisException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RedisException(Exception e) {
        super(e);
    }

    public RedisException(String msg) {
        super(msg);
    }
}
