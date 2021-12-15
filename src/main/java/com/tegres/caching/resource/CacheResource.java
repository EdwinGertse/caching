package com.tegres.caching.resource;

import com.tegres.caching.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cache", produces = MediaType.APPLICATION_JSON_VALUE)
public class CacheResource {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    private CacheManager cacheManager;
    private CacheService cacheService;

    @Autowired
    public CacheResource(CacheManager cacheManager, CacheService cacheService) {
        this.cacheManager = cacheManager;
        this.cacheService = cacheService;
    }

    @DeleteMapping(value = "/clear")
    public ResponseEntity<Void> clearCaches() {
        LOG.info("About to clear all caches");
        cacheService.clearCaches(cacheManager);
        return ResponseEntity.noContent().build();
    }
}
