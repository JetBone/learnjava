package com.jetbone.app.security.config;

import com.jetbone.app.security.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * @author Chris
 * @date 2021-03-20
 */
@Configuration
public class MySpringConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager() {
        MyUserDetailsService myUserDetailsService = new MyUserDetailsService();
        myUserDetailsService.createUser(
                User.builder()
                        .username("admin")
                        .password("{noop}admin")
                        .authorities(AuthorityUtils.NO_AUTHORITIES)
                        .build()
        );
        myUserDetailsService.createUser(
                User.builder()
                        .username("chris")
                        .password("{noop}123456")
                        .authorities(AuthorityUtils.NO_AUTHORITIES)
                        .build()
        );

        return myUserDetailsService;
    }

}
