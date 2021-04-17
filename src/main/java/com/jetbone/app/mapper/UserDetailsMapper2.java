package com.jetbone.app.mapper;

import com.jetbone.app.entity.MyUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @author Chris
 * @date 2021-04-04
 */
@Mapper
public interface UserDetailsMapper2 {

    /**
     * 获取所有用户信息
     * @return 用户信息
     */
    @SelectProvider(value = SqlBuilder.class, method = "selectAll")
    List<MyUserDetails> selectAll();

    /**
     * 根据用户ID获取信息
     * @param userId 用户ID
     * @return 用户信息
     */
    @SelectProvider(value = SqlBuilder.class, method = "findByUserId")
    MyUserDetails findByUserId(Long userId);

    /**
     * 根据用户ID获取信息
     * @param username 用户名称
     * @return 用户信息
     */
    @SelectProvider(value = SqlBuilder.class, method = "findByUsername")
    MyUserDetails findByUsername(String username);

    class SqlBuilder extends SQL {

        private void selectAllColumns() {
            SELECT("id");
            SELECT("username");
            SELECT("password");
        }

        public String selectAll() {
            selectAllColumns();
            FROM("user");

            return toString();
        }

        public String findByUserId(Long userId) {
            selectAllColumns();
            FROM("user");
            WHERE("id = #{userId}");

            return toString();
        }

        public String findByUsername(String username) {
            selectAllColumns();
            FROM("user");
            WHERE("username = #{username}");

            return toString();
        }

    }

}
