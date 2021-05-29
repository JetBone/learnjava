package com.jetbone.app.controller;

import com.jetbone.app.bean.ApiResult;
import com.jetbone.app.controller.param.UserSaveParam;
import com.jetbone.app.entity.MyUserDetails;
import com.jetbone.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chris
 * @date 2021-03-20
 */
@Api(value = "用户", tags = {"用户API"})
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("获取所有用户")
    @GetMapping(value = "/list")
    public ApiResult<List<MyUserDetails>> findAll() {
        return ApiResult.ok(userService.selectAll());
    }

    @ApiOperation("获取用户")
    @GetMapping(value = "/id/{userId}")
    public ApiResult<MyUserDetails> getUserById(@PathVariable Long userId) {
        return ApiResult.ok(userService.findByUserId(userId));
    }

    @ApiOperation("获取用户")
    @GetMapping(value = "/username/{username}")
    public ApiResult<MyUserDetails> getUserById(@PathVariable String username) {
        return ApiResult.ok(userService.findByUsername(username));
    }

    @ApiOperation("创建用户")
    @PostMapping("/create")
    public ApiResult<Object> createUser(@RequestBody UserSaveParam param) {
        Long id = userService.createUser(param);

        return ApiResult.ok(id);
    }

    @ApiOperation("根据用户名删除用户")
    @DeleteMapping("/delete/username/{username}")
    public ApiResult<Object> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);

        return ApiResult.ok();
    }

    @ApiOperation("根据用户名删除用户")
    @DeleteMapping("/delete/id/{userId}")
    public ApiResult<Object> deleteUserByUsername(@PathVariable Long userId) {
        userService.deleteUserByUserId(userId);

        return ApiResult.ok();
    }
}
