package com.fw.springboot_basic.config.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MyUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private String password;
    private String userName;
    private Boolean AccountNonExpired;//是否过期
    private Boolean AccountNonLocked;//是否没被锁定
    private Boolean CredentialsNonExpired;//是否没过期
    private Boolean Enabled;//账号是否可用
    private Collection<? extends GrantedAuthority> Authorities;//权限集合

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        AccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        AccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        CredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(Boolean enabled) {
        Enabled = enabled;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Authorities = authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
