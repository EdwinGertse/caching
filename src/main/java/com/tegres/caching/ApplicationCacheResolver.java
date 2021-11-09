package com.tegres.caching;

import com.tegres.caching.constants.ComponentConstants;
import com.tegres.caching.service.AuthorService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Component(ComponentConstants.APPLICATION_CACHE_RESOLVER)
public class ApplicationCacheResolver implements CacheResolver {

    private final CacheManager cacheManager;

    public ApplicationCacheResolver(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<String> cacheNames = getCacheNames(context);
        if (cacheNames == null) return Collections.emptyList();

        Collection<Cache> result = new ArrayList<>(cacheNames.size());
        if (context.getTarget() instanceof AuthorService) {
            for (String cacheName: cacheNames) {
                Cache cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    throw new IllegalArgumentException(
                            String.format("Unable to find cache: '%s' for operation: %s",
                                    cacheName, context.getOperation()));
                }
                result.add(cache);
            }
        }
        return result;
    }

    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        return context.getOperation().getCacheNames();
    }
}
