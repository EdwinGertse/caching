package com.tegres.caching.service;

import org.springframework.cache.CacheManager;

public interface CacheService {
    void clearCaches(CacheManager cacheManager);
}
