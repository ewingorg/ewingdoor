package com.ewing.core.redis;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.METHOD })
public @interface RedisCache {

    public abstract String key();

    public abstract boolean isList() default false;

    public abstract String[] keyParamNames();

    public abstract String page() default "page";

    public abstract String pageSize() default "pageSize";

}
