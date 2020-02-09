package com.fw.springboot_basic.dao;

import com.fw.springboot_basic.config.auth.MyUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface UserDao {
    /**
     * 通过用户名查找用户信息
     * */
    @Select("SELECT username,password,enabled from user where username = #{username}")
    MyUserDetails findUserByName(@Param("username") String userName);

    /**
     * 根据用户名查找用户角色列表
     * */
    @Select("SELECT r.rolename from role r\n" +
            "where id in (SELECT ur.role_id from user u,user_role ur\n" +
            "where u.id = ur.user_id and u.username = #{username} )\n")
    List<String> findRoleByName(@Param("username") String userName);

    /**
     * 获取所有权限
     * */
    @Select("SELECT p.url from role_permission rp,permission p\n" +
            "where rp.permission_id = p . id and rp.role_id IN ( SELECT ur.role_id from user u,user_role ur\n" +
            "where u.id = ur.user_id and u.username = #{username})")
    List<String> findPermissionByName(@Param("username") String userName);
}
