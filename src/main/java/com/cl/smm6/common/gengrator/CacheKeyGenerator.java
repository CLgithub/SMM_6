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
        MyKey myKey=new MyKey(target.getClass().getName(),method.getName(), Arrays.toString(params));
        //会先去得到key的hashcode,
        //      如果是cacheable,就比较当前的key是否equals已经有的,如果有就取缓存,没有就执行方法,
        //      如果是cache-evict,就比较当前的key是否equals已经有的，如果有就除移已有的
        return myKey;
    }
}
