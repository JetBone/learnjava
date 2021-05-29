package com.jetbone.app.security.config;

import cn.hutool.json.JSONObject;
import com.jetbone.app.bean.ApiResult;
import com.jetbone.app.security.authentication.CaptchaAuthenticationProvider;
import com.jetbone.app.security.authentication.JwtUtil;
import com.jetbone.app.security.service.CaptchaService;
import com.jetbone.app.security.service.MyUserDetailsService;
import com.jetbone.app.service.UserRoleService;
import com.jetbone.app.service.UserService;
import com.nimbusds.jose.JOSEException;
import com.sun.net.httpserver.HttpContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

/**
 * @author Chris
 * @date 2021-03-20
 */
@Slf4j
@Configuration
public class SecurityBeanConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager(UserService userService, UserRoleService userRoleService) {
        MyUserDetailsService myUserDetailsService = new MyUserDetailsService(userService, userRoleService);
//        myUserDetailsService.createUser(
//                User.builder()
//                        .username("admin")
//                        .password("{noop}admin")
//                        .authorities(AuthorityUtils.NO_AUTHORITIES)
//                        .build()
//        );
//        myUserDetailsService.createUser(
//                User.builder()
//                        .username("chris")
//                        .password("{noop}123456")
//                        .authorities(AuthorityUtils.NO_AUTHORITIES)
//                        .build()
//        );

        return myUserDetailsService;
    }

    @Bean
    public CaptchaAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, CaptchaService captchaService) {
        return new CaptchaAuthenticationProvider(userDetailsService, captchaService);
    }

    @Bean
    public AuthenticationManager authenticationManager(CaptchaAuthenticationProvider captchaAuthenticationProvider) {
        return new ProviderManager(Collections.singletonList(captchaAuthenticationProvider));
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {

            try {
                String token = JwtUtil.generateToken(authentication);
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter writer = response.getWriter();
                var result = ApiResult.ok(token);
                var json = new JSONObject(result).toString();

                writer.write(json);
                writer.flush();
                writer.close();

            } catch (JOSEException e) {
                log.error("generate token failed." + e.getMessage());
            }

        };
    }

}
