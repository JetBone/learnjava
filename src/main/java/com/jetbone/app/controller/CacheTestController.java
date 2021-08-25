package com.jetbone.app.controller;

import com.jetbone.app.bean.ApiResult;
import com.jetbone.app.service.CacheService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chris
 * @date 2021-07-03
 */
@Api(value = "缓存测试", tags = {"缓存测试"})
@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
public class CacheTestController {

    private final CacheService cacheService;

    @GetMapping("/put/{key}")
    public ApiResult<String> putCache(@PathVariable String key) {
        var result = cacheService.putCache(key);
        return ApiResult.ok(result);
    }

    @GetMapping("/cacheable/{key}")
    public ApiResult<String> getCache(@PathVariable String key) {
        var result = cacheService.getCache(key);
        return ApiResult.ok(result);
    }

    @GetMapping("/evict/{key}")
    public ApiResult<Object> evictCache(@PathVariable String key) {
        cacheService.evictCache(key);
        return ApiResult.ok();
    }

}
