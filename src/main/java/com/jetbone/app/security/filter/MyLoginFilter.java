package com.jetbone.app.security.filter;

import com.jetbone.app.security.filter.processor.LoginProcessor;
import com.jetbone.app.security.filter.processor.UsernameAndPasswordJsonLoginProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
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
public class MyLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final Log logger = LogFactory.getLog(MyLoginFilter.class);

    private static final RequestMatcher REQUEST_MATCHER = new AntPathRequestMatcher("/process", "POST");

    private final List<LoginProcessor> loginProcessors = new ArrayList<>();

    private final AuthenticationEntryPoint authenticationEntryPoint = new Http403ForbiddenEntryPoint();

    private final UserDetailsService userDetailsService;

    public MyLoginFilter(List<LoginProcessor> loginProcessors, UserDetailsService userDetailsService) {
        super(REQUEST_MATCHER);
        if (loginProcessors == null || loginProcessors.size() == 0) {
            this.loginProcessors.add(defaultLoginProcessor());
        } else {
            this.loginProcessors.addAll(loginProcessors);
        }
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (REQUEST_MATCHER.matches(request)) {
            Optional<LoginProcessor> loginProcessorOptional = loginProcessors.stream().filter(t -> t.isSupport(request)).findFirst();
            if (loginProcessorOptional.isEmpty()) {
                logger.info("不支持的登录方式");
            } else {
                return loginProcessorOptional.get().process(request, response);
            }
        }

        return null;
    }

    private LoginProcessor defaultLoginProcessor() {
        return new UsernameAndPasswordJsonLoginProcessor();
    }
}
