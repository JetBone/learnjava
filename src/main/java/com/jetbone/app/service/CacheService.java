package com.jetbone.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Chris
 * @date 2021-07-03
 */
@Slf4j
@Service
public class CacheService {

    private static final String CACHE_NAME = "cache";

    @CachePut(cacheNames = CACHE_NAME, key = "#key")
    public String putCache(String key) {
        var value = LocalDateTime.now().toString();
        log.info("put cache: key: " + key + " value: " + value);
        return value;
    }

    @Cacheable(cacheNames = CACHE_NAME, key = "#key")
    public String getCache(String key) {
        var value = LocalDateTime.now().toString();
        log.info("cacheable cache: key: " + key + " value: " + value);
        return value;
    }

    @CacheEvict(cacheNames = CACHE_NAME, keyGenerator = "customKeyGenerator")
    public void evictCache(String key) {
        log.info("evict cache: key: " + key);
    }



}
