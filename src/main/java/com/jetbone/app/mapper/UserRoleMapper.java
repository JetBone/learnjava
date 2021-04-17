package com.jetbone.app.mapper;

import com.jetbone.app.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @author Chris
 * @date 2021-04-17
 */
@Mapper
public interface UserRoleMapper {

    /**
     * 根据用户ID获取角色信息
     * @param userId 用户ID
     * @return 角色信息
     */
    @SelectProvider(type = SqlBuilder.class, method = "findUserRolesByUserId")
    List<UserRole> findUserRolesByUserId(Long userId);

    /**
     * 根据用户名称获取角色信息
     * @param username 用户名称
     * @return 角色信息
     */
    @SelectProvider(type = SqlBuilder.class, method = "findUserRolesByUsername")
    List<UserRole> findUserRolesByUsername(String username);


    class SqlBuilder extends SQL {

        private void selectAllColumn() {
            SELECT("id");
            SELECT("user_id");
            SELECT("username");
            SELECT("role");
        }

        public String findUserRolesByUserId(Long userId) {
            selectAllColumn();
            FROM("user_roles");
            WHERE("user_id = #{userId}");

            return toString();
        }

        public String findUserRolesByUsername(String username) {
            selectAllColumn();
            FROM("user_roles");
            WHERE("username = #{username}");

            return toString();
        }

    }

}
