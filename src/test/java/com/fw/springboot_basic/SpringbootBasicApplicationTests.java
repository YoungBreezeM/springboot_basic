package com.fw.springboot_basic;

import com.fw.springboot_basic.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootBasicApplicationTests {
    @Autowired
    private UserDao userDao;
    @Test
    void contextLoads() {
//        List<String> rs = userDao.findRoleByName("yqf");
//        System.out.println(rs);
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        System.out.println(bCryptPasswordEncoder.encode("123456"));
//
    }

}
