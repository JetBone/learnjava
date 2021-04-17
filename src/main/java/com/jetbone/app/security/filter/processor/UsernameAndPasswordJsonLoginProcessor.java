package com.jetbone.app.security.filter.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Chris
 * @date 2021-03-21
 */
public class UsernameAndPasswordJsonLoginProcessor extends AbstractJsonLoginProcessor {

    private static final Logger logger = LoggerFactory.getLogger(UsernameAndPasswordJsonLoginProcessor.class);

    @Override
    public LoginType getLoginType() {
        return LoginType.JSON_U_P;
    }

    @Override
    public Authentication process(HttpServletRequest request, HttpServletResponse response) {
        logger.info("执行 JsonLoginProcessor");

        return null;
    }
}
