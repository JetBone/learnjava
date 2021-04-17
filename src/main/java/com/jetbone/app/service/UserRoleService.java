package com.jetbone.app.service;

import com.jetbone.app.entity.UserRole;
import com.jetbone.app.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chris
 * @date 2021-04-17
 */
@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleMapper userRoleMapper;

    public List<UserRole> findUserRolesByUserId(Long userId) {
        return userRoleMapper.findUserRolesByUserId(userId);
    }

    public List<UserRole> findUserRolesByUsername(String username) {
        return userRoleMapper.findUserRolesByUsername(username);
    }

}
