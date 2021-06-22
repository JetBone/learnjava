package com.jetbone.app.bean;

import lombok.Data;

/**
 * Created by Chris on 2020/4/9
 * @author chrischan
 */

@Data
public class ApiResult<T> {

    private static final ApiResult<Object> OK = new ApiResult<>(
            200,
            "successful"
            );

    private int code;

    private String message;

    private T data;

    public ApiResult() {
    }

    private ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<>(200, "successful", data);
    }

    public static ApiResult<Object> ok() {
        return OK;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
