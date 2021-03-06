package com.jetbone.app.mapper;

import com.jetbone.app.entity.MyUserDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Chris
 * @date 2021-04-04
 */
@Mapper
public interface UserDetailsMapper {

    /**
     * 获取所有用户信息
     * @return 用户信息
     */
    List<MyUserDetails> selectAll();

    /**
     * 根据用户ID获取信息
     * @param userId 用户ID
     * @return 用户信息
     */
    MyUserDetails findByUserId(Long userId);

    /**
     * 根据用户ID获取信息
     * @param username 用户名称
     * @return 用户信息
     */
    MyUserDetails findByUsername(String username);
}
