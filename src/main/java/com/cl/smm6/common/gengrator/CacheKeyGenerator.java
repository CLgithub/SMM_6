package com.cl.smm6.common.gengrator;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by L on 17/2/3.
 * 自定义 cacheKEY生成策略
 */
public class CacheKeyGenerator implements KeyGenerator{
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return method.toString()+ Arrays.toString(params);
    }
}
