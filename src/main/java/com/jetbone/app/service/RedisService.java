package com.jetbone.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Chris
 * @date 2021-03-14
 */
@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate redisTemplate;

    public void redisTest() {
        System.out.println("############ START ############");
//        redisTemplate.opsForValue().set("Spring key", "Spring value");
        System.out.println("############ END ############");
    }

}
