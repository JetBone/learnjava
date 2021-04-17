package com.jetbone.app.security.config;

import com.jetbone.app.security.CaptchaAuthenticationProvider;
import com.jetbone.app.security.service.CaptchaService;
import com.jetbone.app.security.service.MyUserDetailsService;
import com.jetbone.app.service.UserRoleService;
import com.jetbone.app.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author Chris
 * @date 2021-03-20
 */
@Configuration
public class MySpringConfiguration {

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

}
