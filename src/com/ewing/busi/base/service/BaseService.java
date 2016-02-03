package com.ewing.busi.base.service;

import javax.annotation.Resource;

import com.ewing.common.constants.ResponseCode;
import com.ewing.common.exception.BusinessException;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.util.IntegerUtil;

/**
 * 通过Service公共抽象类
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2015年10月23日
 *
 */
public abstract class BaseService {

    @Resource
    private BaseDao baseDao;
    
    public <T> T findById(Integer id,Class<T> clazz){
        if(IntegerUtil.nullOrZero(id) || null == clazz){
            return null;
        }
        
        return baseDao.findOne(id, clazz);
    }
    
    protected static <T> boolean checkNotNull(T t) {
        return null != t ? true : false;
    }

    protected static <T> T checkNotNull(T t, String msg) {
        if (checkNotNull(t)) {
            return t;
        } else {
            throw new BusinessException(ResponseCode.INTERNAL_ERROR, msg);
        }
    }

    /**
     * t 为null或false返回true， t为tue返回false
     * @param t
     * @param msg
     */
    protected static boolean checkFalse(Boolean t) {
        return t == null || t == false ? true : false;
    }

    /**
     * true 时候抛出异常
     * @param t
     * @param msg
     */
    protected static boolean checkFalse(Boolean t, String msg) {
        if (checkFalse(t)) {
            return true;
        } else {
            throw new BusinessException(ResponseCode.INTERNAL_ERROR, msg);
        }
    }

}
