package com.jetbone.app.security.filter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Chris
 * @date 2021-03-21
 */
public abstract class AbstractLoginProcessor implements LoginProcessor {

    private static final String LOGIN_TYPE_KEY = "type";

    @Override
    public boolean isSupport(HttpServletRequest request) {
        String loginType = request.getParameter(LOGIN_TYPE_KEY);
        return this.getLoginType().name().equalsIgnoreCase(loginType);
    }
}
