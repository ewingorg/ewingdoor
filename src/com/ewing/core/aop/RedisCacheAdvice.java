package com.ewing.core.aop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import org.apache.axis.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.ewing.core.redis.RedisCache;
import com.ewing.core.redis.RedisManage;

/**
 * redis的AOP拦截
 * 
 * @author tansonlam
 */
@Aspect
public class RedisCacheAdvice {

    private String[] paramNames;

    @Around("execution(* com.ewing.busi.resource.service.*.*(..))  && @annotation(cache)")
    public Object aroundMethod(ProceedingJoinPoint pjd, RedisCache cache) throws Throwable {

        if (cache != null && !StringUtils.isEmpty(cache.key())) {
            analyseMethodInfo(pjd);
            String key = cacheKey(pjd, cache);
            RedisManage redis = RedisManage.getInstance();
            // 处理LIST
            if (cache.isList()) {
                ParamValue page = getOneParamVale(pjd, cache.page());
                ParamValue pageSize = getOneParamVale(pjd, cache.pageSize());
                if (page != null && !StringUtils.isEmpty(page.getValue()) && pageSize != null
                        && !StringUtils.isEmpty(pageSize.getValue())) {
                    String pageKey = key + "_page=" + page.getValue() + "_pageSize="
                            + pageSize.getValue();
                    List cacheList = redis.lrange(pageKey, 0, -1);
                    if (cacheList != null && !cacheList.isEmpty())
                        return cacheList;
                }

            }
            // 处理普通对象类型
            else {
                Object cacheResult = redis.get(key);
                if (cacheResult != null)
                    return cacheResult;
            }
        }

        Object retObj = pjd.proceed();
        if (retObj == null)
            return null;
        if (cache != null && !StringUtils.isEmpty(cache.key())) {
            String key = cacheKey(pjd, cache);
            RedisManage redis = RedisManage.getInstance();
            // 处理LIST
            if (cache.isList()) {
                List listObj = (List) retObj;
                if (listObj != null && !listObj.isEmpty()) {
                    ParamValue page = getOneParamVale(pjd, cache.page());
                    ParamValue pageSize = getOneParamVale(pjd, cache.pageSize());
                    if (page != null && !StringUtils.isEmpty(page.getValue()) && pageSize != null
                            && !StringUtils.isEmpty(pageSize.getValue())) {
                        String pageKey = key + "_page=" + page.getValue() + "_pageSize="
                                + pageSize.getValue();
                        redis.rpush(pageKey, (List) retObj);
                        String mapKey = key + "_map";
                        redis.hset(mapKey, pageKey, pageKey);
                    }
                }

            }
            // 处理普通对象类型
            else {
                if (retObj != null)
                    redis.set(key, retObj);
            }
        }
        return retObj;
    }

    private String cacheKey(ProceedingJoinPoint pjd, RedisCache cache) {
        StringBuffer cacheKey = new StringBuffer();
        cacheKey.append(cache.key());
        if (cache.keyParamNames() != null && cache.keyParamNames().length > 0) {
            List<ParamValue> paramValues = getMultiParamValues(pjd, cache.keyParamNames());
            for (int i = 0; i < paramValues.size(); i++) {
                if (i < paramValues.size())
                    cacheKey.append("_");
                cacheKey.append(paramValues.get(i).name + "=" + paramValues.get(i).value);
            }
        }
        return cacheKey.toString();
    }

    private void analyseMethodInfo(ProceedingJoinPoint pjd) {
        String className = pjd.getTarget().getClass().getName();
        String methodName = pjd.getSignature().getName();
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(this.getClass()));

        try {
            CtClass cc = pool.get(className);
            CtMethod cm = cc.getDeclaredMethod(methodName);

            // 使用javaassist的反射方法获取方法的参数名
            MethodInfo methodInfo = cm.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
                    .getAttribute(LocalVariableAttribute.tag);

            paramNames = new String[cm.getParameterTypes().length];
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            for (int i = 0; i < paramNames.length; i++)
                paramNames[i] = attr.variableName(i + pos);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取单个入参名称的参数值
     * 
     * @param pjd
     * @param keyParamName 参数名称
     * @return
     */
    private ParamValue getOneParamVale(ProceedingJoinPoint pjd, String keyParamName) {
        List<ParamValue> list = getMultiParamValues(pjd, new String[] { keyParamName });
        if (list.isEmpty())
            return null;
        return list.get(0);
    }

    /**
     * 获取多个入参名称的参数值
     * 
     * @param pjd
     * @param keyParamNames 参数名称数组
     * @return
     */
    private List<ParamValue> getMultiParamValues(ProceedingJoinPoint pjd, String[] keyParamNames) {
        if (paramNames == null || pjd.getArgs().length == 0)
            return Collections.EMPTY_LIST;
        List<ParamValue> paramValues = new ArrayList<ParamValue>();
        // paramNames即参数名
        Object[] args = pjd.getArgs();

        for (String name : keyParamNames) {
            for (int i = 0; i < paramNames.length; i++) {
                if (name.equalsIgnoreCase(paramNames[i])) {
                    ParamValue paramValue = new ParamValue();
                    if (args[i] == null)
                        continue;
                    paramValue.value = args[i].toString();
                    paramValue.name = name;
                    paramValues.add(paramValue);
                    break;
                }
            }
        }
        return paramValues;
    }

    /**
     * 解析方法参数值的封装对象
     */
    class ParamValue {
        /**
         * 参数名称
         */
        private String name;
        /**
         * 参数值
         */
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }
}
