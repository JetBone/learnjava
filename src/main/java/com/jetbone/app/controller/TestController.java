package com.jetbone.app.controller;

import com.jetbone.app.bean.DefaultQuery;
import com.jetbone.app.bean.ApiResult;
import com.jetbone.app.controller.param.PostParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chris
 * @date 2019-06-20
 */
@Slf4j
@Api(value = "测试", tags = {"测试API"})
@RestController
@RequestMapping("/hello")
public class TestController {

    @ApiOperation("hello world")
    @GetMapping("/world")
    public ApiResult<String> hello(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request);
        System.out.println(response);

        return ApiResult.ok("hello");
    }

    @ApiOperation("测试")
    @GetMapping("/test")
    public ApiResult<String> test(DefaultQuery query) {

        System.out.println(query.getDate());

        return ApiResult.ok("测试成功");
    }


    @ApiOperation("Get请求测试")
    @GetMapping("/get")
    public ApiResult<String> getTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread current = Thread.currentThread();
            log.info("Tread - " + current.getName() + " - sleep time: " + i);
            Thread.sleep(1000);
        }

        return ApiResult.ok("Get finished");
    }

    @ApiOperation("Post请求测试")
    @PostMapping("/post")
    public ApiResult<String> postTest(@RequestBody PostParam param) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread current = Thread.currentThread();
            log.info("Tread - " + current.getName() + " - sleep time: " + i);
            Thread.sleep(1000);
        }

        return ApiResult.ok(param.toString());
    }


}
