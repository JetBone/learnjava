package com.jetbone.app.security.config;

import com.jetbone.app.security.filter.FormLoginProcessor;
import com.jetbone.app.security.filter.JsonLoginProcessor;
import com.jetbone.app.security.filter.LoginProcessor;
import com.jetbone.app.security.filter.MyLoginFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author Chris
 * @date 2021-03-20
 */
@Configuration
@ConditionalOnClass(WebSecurityConfigurerAdapter.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class MySpringSecurityConfiguration {

    @Bean
    public FormLoginProcessor formLoginProcessor() {
        return new FormLoginProcessor();
    }

    @Bean
    public JsonLoginProcessor jsonLoginProcessor() {
        return new JsonLoginProcessor();
    }

    @Bean
    public MyLoginFilter myLoginFilter(List<LoginProcessor> loginProcessors) {
        return new MyLoginFilter(loginProcessors);
    }

    @Configuration
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    static class DefaultWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Resource
        private MyLoginFilter myLoginFilter;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            super.configure(auth);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            super.configure(web);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(myLoginFilter, UsernamePasswordAuthenticationFilter.class)
                    .formLogin()
                    .loginProcessingUrl("/process");
//                    .successForwardUrl("/login/success")
//                    .failureForwardUrl("/login/failure");
        }
    }


}
