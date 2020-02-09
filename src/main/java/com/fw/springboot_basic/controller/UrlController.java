package com.fw.springboot_basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UrlController {
    @RequestMapping("/index")
    public String getLoginPage(){
        System.out.println("index");

        return "index";
    }

    @RequestMapping("/page1")
    public String getPage1(){
        return "page1";
    }
    @RequestMapping("/page2")
    public String getPage2(){
        return "page2";
    }
}
