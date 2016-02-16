package com.ewing.core.voinfo;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ewing.core.voinfo.entity.EntityInfo;
import com.ewing.core.voinfo.entity.FieldInfo;
import com.google.common.collect.Maps;

/**
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月26日
 *
 */
public abstract class FieldAnnotationManager {
    
    protected static volatile FieldAnnotationManager instance = null;

    private static Map<String, EntityInfo> entityInfoMap = Maps.newHashMap();

    /**
     * 获取属性FieldInfo
     * 
     * @param className
     * @param fieldName
     * @author Joeson
     */
    public FieldInfo getFieldInfo(String className, String field) {
        if (StringUtils.isEmpty(className) || StringUtils.isEmpty(field)) {
            return new FieldInfo();
        }

        EntityInfo entityInfo = this.getEntityInfo(className);
        return null == entityInfo ? null : entityInfo.getFieldName(field);
    }

    /**
     * 获取对象EntityInfo
     * 
     * @author Joeson
     */
    public EntityInfo getEntityInfo(String className) {
        if (StringUtils.isEmpty(className)) {
            return new EntityInfo();
        }

        EntityInfo entityInfo = entityInfoMap.get(className);
        if (null == entityInfo) {
            // 解析className
            entityInfo = reloadEntityInfo(className);
            entityInfoMap.put(className, entityInfo);
        }
        return null == entityInfo ? new EntityInfo() : entityInfo;
    }
    
    protected abstract EntityInfo reloadEntityInfo(String className);

}
