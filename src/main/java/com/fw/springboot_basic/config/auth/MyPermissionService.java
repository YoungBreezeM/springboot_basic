package com.fw.springboot_basic.config.auth;

import com.fw.springboot_basic.dao.UserDao;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("myPermissionService")
public class MyPermissionService {
    @Autowired
    private UserDao userDao;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public Boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            //获取用户名
            String userName = ((UserDetails) principal).getUsername();
            //获取全部权限列表
            List<String> permissions = userDao.findPermissionByName(userName);
            //判断列表中是否包含该路径
            return permissions.stream()
                    .anyMatch(
                            url->antPathMatcher.match(url,request.getRequestURI())
                    );
        }
        return false;
    }
}
