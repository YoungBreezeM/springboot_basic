package com.fw.springboot_basic.config.auth;
import com.fw.springboot_basic.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public MyUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //配置用户基础信息
        MyUserDetails myUserDetails = userDao.findUserByName(s);
        //配置角色列表
        List<String> roles = userDao.findRoleByName(s);
        //配置权限列表
        List<String> permission = userDao.findPermissionByName(s);
        //角色是一个特殊的权限
        roles = roles.stream()
                .map(rc->"ROLE_"+rc)
                .collect(Collectors.toList());
        permission.addAll(roles);

        //注入权限列表的值
        myUserDetails.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",",permission)
                )
        );
        return myUserDetails;

    }
}
