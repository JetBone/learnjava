package com.jetbone.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Chris on 2020/4/9
 */

@Data
@NoArgsConstructor
public class DefaultResult<T> {

    private int code;

    private String message;

    private T data;

}
