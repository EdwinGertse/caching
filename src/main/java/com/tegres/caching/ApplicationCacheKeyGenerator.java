package com.tegres.caching;

import com.tegres.caching.constants.ComponentConstants;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component(ComponentConstants.APPLICATION_CACHE_KEY_GENERATOR)
public class ApplicationCacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getSimpleName()).append(".").append(method.getName());

        for (Object param : params) {
            sb.append(".").append(param.getClass().getSimpleName()).append(":").append(param);
        }

        return sb.toString();
    }
}
