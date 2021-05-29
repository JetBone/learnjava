package com.jetbone.app.controller.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Chris
 * @date 2021-05-02
 */
@Data
public class UserSaveParam implements Serializable {

    private String username;

    private String password;
}
