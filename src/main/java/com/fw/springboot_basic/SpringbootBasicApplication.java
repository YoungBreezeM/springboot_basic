package com.fw.springboot_basic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.fw.springboot_basic.dao"})
public class SpringbootBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBasicApplication.class, args);
    }

}
