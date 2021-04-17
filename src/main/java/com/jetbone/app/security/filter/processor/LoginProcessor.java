package com.jetbone.app.security.filter.processor;

import com.jetbone.app.security.LoginBody;
import org.springframework.security.core.Authentication;

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
     * 执行操作
     * @param request
     * @param response
     * @return
     */
    Authentication process(HttpServletRequest request, HttpServletResponse response);

    /**
     * 解析登陆信息
     * @param request request
     * @return 登陆信息
     */
    LoginBody getLoginBody(HttpServletRequest request);

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
        JSON_U_P,

        /**
         * captcha
         */
        JSON_CAPTCHA,
        ;
    }

}
