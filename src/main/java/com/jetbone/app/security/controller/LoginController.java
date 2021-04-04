package com.jetbone.app.security.controller;

import com.jetbone.app.bean.ApiResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chris
 * @date 2021-03-21
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    @PostMapping("success")
    public ApiResult<UserDetails> loginSuccessful() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ApiResult.ok(userDetails);
    }

    @PostMapping("failure")
    public ApiResult<String> loginFailure() {
        return ApiResult.ok("认证失败");
    }
}
