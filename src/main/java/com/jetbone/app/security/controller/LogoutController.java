package com.jetbone.app.security.controller;

import com.jetbone.app.bean.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chris
 * @date 2021-04-17
 */
@RestController
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping("/success")
    public ApiResult<String> logoutSuccess() {
        return ApiResult.ok("退出成功");
    }

}
