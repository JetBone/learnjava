package com.jetbone.app.security.authentication;

import lombok.Data;

/**
 * @author Chris
 * @date 2021-03-21
 */
@Data
public class LoginBody {

    private String username;

    private String password;

    private String type;
}
