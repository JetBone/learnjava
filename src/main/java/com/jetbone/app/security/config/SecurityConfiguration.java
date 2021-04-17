package com.jetbone.app.security.config;

import com.jetbone.app.security.filter.processor.CaptchaLoginProcessor;
import com.jetbone.app.security.filter.processor.UsernameAndPasswordJsonLoginProcessor;
import com.jetbone.app.security.filter.processor.LoginProcessor;
import com.jetbone.app.security.filter.MyLoginFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Chris
 * @date 2021-03-20
 */
@Configuration
@ConditionalOnClass(WebSecurityConfigurerAdapter.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfiguration {

    @Bean
    public UsernameAndPasswordJsonLoginProcessor jsonLoginProcessor() {
        return new UsernameAndPasswordJsonLoginProcessor();
    }

    @Bean
    public CaptchaLoginProcessor captchaMyLoginAuthentication(AuthenticationManager authenticationManager) {
        return new CaptchaLoginProcessor(authenticationManager);
    }

    @Bean
    public MyLoginFilter myLoginFilter(List<LoginProcessor> loginProcessors,
                                       UserDetailsService userDetailsService,
                                       AuthenticationManager authenticationManager) {

        MyLoginFilter loginFilter = new MyLoginFilter(loginProcessors, userDetailsService);
        loginFilter.setAuthenticationManager(authenticationManager);
        loginFilter.setAuthenticationSuccessHandler(new ForwardAuthenticationSuccessHandler("/login/success"));
        loginFilter.setAuthenticationFailureHandler(new ForwardAuthenticationFailureHandler("/login/failure"));
        return loginFilter;
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
                    .cors()
                    .and()
                    .authorizeRequests()
                    // 登陆接口
                    .antMatchers("/login/captcha/**").permitAll()
                    // swagger 文档
//                    .antMatchers("/swagger-ui/**").permitAll()
//                    .antMatchers("/swagger-resources/**").permitAll()
//                    .antMatchers("/webjars/**").permitAll()
//                    .antMatchers("/*/api-docs").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(myLoginFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }


}
