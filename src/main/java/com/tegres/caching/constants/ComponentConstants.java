package com.tegres.caching.constants;

public class ComponentConstants {

    public static final String INMEMORY_CACHE_MANAGER = "inMemoryCacheManager";
    public static final String CUSTOM_CACHE_RESOLVER = "customCacheResolver";
    public static final String APPLICATION_CACHE_RESOLVER = "applicationCacheResolver";
    public static final String APPLICATION_CACHE_KEY_GENERATOR = "applicationCacheKeyGenerator";

    private ComponentConstants() {
        throw new RuntimeException("Class should be used in a static context");
    }
}
