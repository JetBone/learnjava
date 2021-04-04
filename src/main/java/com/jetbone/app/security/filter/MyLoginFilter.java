package com.jetbone.app.security.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Chris
 * @date 2021-03-21
 */
public class MyLoginFilter extends GenericFilterBean {

    private static final Log logger = LogFactory.getLog(MyLoginFilter.class);

    private static final RequestMatcher REQUEST_MATCHER = new AntPathRequestMatcher("/process", "POST");

    private final List<LoginProcessor> loginProcessors = new ArrayList<>();

    private final AuthenticationEntryPoint authenticationEntryPoint = new Http403ForbiddenEntryPoint();

    public MyLoginFilter(List<LoginProcessor> loginProcessors) {
        if (loginProcessors == null || loginProcessors.size() == 0) {
            this.loginProcessors.add(defaultLoginProcessor());
        } else {
            this.loginProcessors.addAll(loginProcessors);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (REQUEST_MATCHER.matches(request)) {
            Optional<LoginProcessor> loginProcessorOptional = loginProcessors.stream().filter(t -> t.isSupport(request)).findFirst();
            if (loginProcessorOptional.isEmpty()) {
                logger.info("不支持的登录方式");
            } else {
                loginProcessorOptional.get().process(request, response);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private LoginProcessor defaultLoginProcessor() {
        return new FormLoginProcessor();
    }
}
