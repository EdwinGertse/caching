package com.tegres.caching.service;

import com.tegres.caching.constants.ComponentConstants;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service(ComponentConstants.SERVICE_CACHE)
public class CacheServiceImpl implements CacheService {

    @Override
    public void clearCaches(CacheManager cacheManager) {
        cacheManager.getCacheNames().forEach(cacheName ->
                Objects.requireNonNull(cacheManager.getCache(cacheName))
                        .clear());
    }
}
