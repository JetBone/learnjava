package com.jetbone.app.service;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Chris
 * @date 2021-07-03
 */
@Component
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public String generate(Object target, Method method, Object... params) {
        var key = "default";
        if (params != null && params.length == 1) {
            key = params[0].toString();
        }

        return key;
    }
}
