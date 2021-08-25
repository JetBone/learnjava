package com.jetbone.app.controller;

import com.jetbone.app.async.AsyncTestService;
import com.jetbone.app.bean.ApiResult;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chris
 * @date 2021-08-23
 */
@Api(value = "Async测试", tags = {"Async测试"})
@RestController
@RequestMapping("/async")
@RequiredArgsConstructor
public class AsyncController {

    private final AsyncTestService asyncTestService;

    @GetMapping("/test/{input}")
    public ApiResult<Object> syncTest(@PathVariable String input) {
        asyncTestService.testSync(input);
        return ApiResult.ok();
    }
}
