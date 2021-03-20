package com.jetbone.app.controller;

import com.jetbone.app.bean.DefaultQuery;
import com.jetbone.app.bean.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chris
 * @date 2019-06-20
 */
@Api(value = "测试", tags = {"测试API"})
@RestController
@RequestMapping("/hello")
public class TestController {

    @ApiOperation("hello world")
    @RequestMapping(value = "/world", method = RequestMethod.GET)
    public ApiResult<Object> hello(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request);
        System.out.println(response);

        return ApiResult.ok();
    }

    @ApiOperation("测试")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ApiResult<Object> test(DefaultQuery query) {

        System.out.println(query.getDate());

        return ApiResult.ok();
    }


}
