package com.jetbone.app.service;

import com.jetbone.app.controller.param.UserSaveParam;
import com.jetbone.app.entity.MyUserDetails;
import com.jetbone.app.mapper.UserDetailsMapper2;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chris
 * @date 2021-04-04
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    private final UserDetailsMapper userDetailsMapper;
    private final UserDetailsMapper2 userDetailsMapper;

    public List<MyUserDetails> selectAll() {
        logger.info("[USER_SERVICE] select all");
        return userDetailsMapper.selectAll();
    }

    public MyUserDetails findByUserId(Long userId) {
        logger.info("[USER_SERVICE] findByUserId, userId:" + userId);
        return userDetailsMapper.findByUserId(userId);
    }

    public MyUserDetails findByUsername(String username) {
        logger.info("[USER_SERVICE] findByUsername, username:" + username);
        return userDetailsMapper.findByUsername(username);
    }

    public Long createUser(UserSaveParam param) {
        logger.info("创建用户：username: " + param.getUsername() + " password: " + param.getPassword());
        param.setPassword("{bcrypt}" + passwordEncoder.encode(param.getPassword()));

        Long id = userDetailsMapper.createUser(param);

        return id;
    }

    public void deleteUserByUsername(String username) {
        userDetailsMapper.deleteUserByUsername(username);
    }

    public void deleteUserByUserId(Long userId) {
        userDetailsMapper.deleteUserByUserId(userId);
    }

}
