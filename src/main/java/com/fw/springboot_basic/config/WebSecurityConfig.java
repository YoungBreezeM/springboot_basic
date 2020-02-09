package com.fw.springboot_basic.config;

import com.fw.springboot_basic.config.auth.MyUserDetailsService;
import com.fw.springboot_basic.config.auth.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    /**
     * 启用spring_security http  --模块
     * 启用基础的通信类型 ---  httpBasic
     * 该模式为极度简化模式容易被破解
     * authorizeRequests().anyRequest() 任何请求都要经过校验
     *
     * */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//       http.httpBasic()
//               .and()
//               .authorizeRequests().anyRequest()
//               .authenticated();
//    }
    /**
     * csrf().disable() 跨站防御攻击
     * 启用表单登录
     * fromLogin -- 启用表单验证
     * antMatchers.permitAll()  不进行验证的路径的页面
     * antMatchers.hasAnyAuthority 配置那些用户对应有那些权限
     * authenticated 登录就可以访问
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭跨站工具、、攻击防御
        http.csrf().disable();
        //配置用户登录
        http.formLogin()
                    .loginPage("/login.html")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/index")
                .and()
                    .authorizeRequests()
                    .antMatchers("/login","/login.html").permitAll()
                    .antMatchers("/index").authenticated()
                    .anyRequest().access("@myPermissionService.hasPermission(request,authentication)");//权限验证
        //配置session
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().migrateSession()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(new SessionStatus());
    }

    /**
     * 用户角色配置及其权限配置
     * 启用 auth.inMemoryAuthentication() 配置用户权限
     * 配置用户和密码及其角色
     * 对信息进行 BCrypt 加密
     * */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("123456"))
//                .roles("user")
//                    .and()
//                .withUser("admin")
//                .password(passwordEncoder().encode("123456"))
//                .roles("admin")
//                    .and()
//                .passwordEncoder(passwordEncoder());
//    }
    /**
     * 动态配置用户角色密码
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 配置BCrypt 密码加密
     * */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 忽略对静态资源的拦截
     * */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("css/***","js/**","img/**","fonts/**");
    }
}
