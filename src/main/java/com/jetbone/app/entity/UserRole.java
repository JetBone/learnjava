package com.jetbone.app.entity;

import lombok.Data;

/**
 * @author Chris
 * @date 2021-04-17
 */
@Data
public class UserRole {

    private Long id;

    private Long userId;

    private String role;
}
