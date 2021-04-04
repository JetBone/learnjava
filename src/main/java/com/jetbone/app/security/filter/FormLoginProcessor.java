package com.jetbone.app.security.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Chris
 * @date 2021-03-21
 */
public class FormLoginProcessor extends AbstractLoginProcessor {

    private static final Log logger = LogFactory.getLog(FormLoginProcessor.class);

    @Override
    public LoginType getLoginType() {
        return LoginType.FORM;
    }

    @Override
    public String getUsername(HttpServletRequest request) {
        return request.getParameter("username");
    }

    @Override
    public String getPassword(HttpServletRequest request) {
        return request.getParameter("password");
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        logger.info("执行 FormLoginProcessor");
    }
}
