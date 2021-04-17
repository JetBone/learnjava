package com.jetbone.app.service;

import com.jetbone.app.entity.MyUserDetails;
import com.jetbone.app.mapper.UserDetailsMapper2;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

}
