package com.tegres.caching;

import com.tegres.caching.constants.ComponentConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
public class CachingConfiguration {

    @CacheEvict(allEntries = true, cacheManager = ComponentConstants.INMEMORY_CACHE_MANAGER)
    @Scheduled(fixedDelay = 10000)
    public void evict() {}

    @Bean(ComponentConstants.CUSTOM_CACHE_RESOLVER)
    public CacheResolver cacheResolver(
            @Qualifier(ComponentConstants.INMEMORY_CACHE_MANAGER) CacheManager cacheManager) {
        return new ApplicationCacheResolver(cacheManager);
    }

    @Bean(ComponentConstants.INMEMORY_CACHE_MANAGER)
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }
}
