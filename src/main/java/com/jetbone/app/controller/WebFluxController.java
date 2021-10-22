package com.jetbone.app.controller;

import com.jetbone.app.bean.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Chris
 * @date 2021-10-21
 */
@RestController
@RequestMapping("/webflux")
public class WebFluxController {

    @GetMapping("/normal/{test}")
    public ApiResult<String> normalTest(@PathVariable String test) {
        return ApiResult.ok(test);
    }

    @GetMapping("/mono/{test}")
    public Mono<ApiResult<String>> monoTest(@PathVariable String test) {
        return Mono.just(test).map(v -> ApiResult.ok(v + " webflux"));
    }
}
