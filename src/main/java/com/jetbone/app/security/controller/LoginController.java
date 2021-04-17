package com.jetbone.app.security.controller;

import com.jetbone.app.bean.ApiResult;
import com.jetbone.app.security.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chris
 * @date 2021-03-21
 */
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final CaptchaService captchaService;

    @PostMapping("success")
    public ApiResult<UserDetails> loginSuccessful() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ApiResult.ok(userDetails);
    }

    @PostMapping("failure")
    public ApiResult<String> loginFailure() {
        return ApiResult.ok("认证失败");
    }

    @GetMapping("/captcha/{phone}")
    public ApiResult<String> sendCaptcha(@PathVariable String phone) {
        return ApiResult.ok(captchaService.sendCaptcha(phone));
    }
}
