package com.jetbone.app.controller;

import com.jetbone.app.bean.ApiResult;
import com.jetbone.app.bean.UserDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chris
 * @date 2021-03-20
 */
@Api(value = "用户", tags = {"用户API"})
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation("获取用户")
    @GetMapping(value = "/id/{userId}")
    @ResponseBody
    public ApiResult<UserDetails> getUserById(@PathVariable Long userId) {
        return ApiResult.ok(new UserDetails() {{
            setId(0L);
            setName("admin");
            setAge(999);
        }});
    }


}
