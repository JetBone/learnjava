package com.jetbone.app.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Chris
 * @date 2021-03-21
 */
public interface LoginProcessor {

    boolean isSupport(HttpServletRequest request);

    /**
     * 获取当前 processor 对应的 login type
     * @return
     */
    LoginType getLoginType();

    /**
     * 获取用户名
     * @param request
     * @return
     */
    String getUsername(HttpServletRequest request);

    /**
     * 获取密码
     * @param request
     * @return
     */
    String getPassword(HttpServletRequest request);

    /**
     * 执行操作
     * @param request
     * @param response
     */
    void process(HttpServletRequest request, HttpServletResponse response);

    /**
     * 登录方式
     */
    enum LoginType {

        /**
         * FORM 表单
         */
        FORM,

        /**
         * JSON body
         */
        JSON
        ;
    }

}
