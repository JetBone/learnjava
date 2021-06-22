package com.jetbone.app.controller.param;

import lombok.Data;

/**
 * @author Chris
 * @date 2021-06-22
 */
@Data
public class PostParam {

    private String value;

    private String code;

    @Override
    public String toString() {
        return "PostParam{" +
                "value='" + value + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
