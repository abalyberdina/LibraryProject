package com.epam.library.support;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epam.library.domain.LoginData;

public class SecurityUserDetails implements UserDetails {
    private LoginData loginData;
    private Set<GrantedAuthority> authorities;

    public SecurityUserDetails() {
    }
    
    public SecurityUserDetails(LoginData loginData) {
        this.loginData = loginData;
        this.authorities = new HashSet<>();
        authorities.add(loginData.getUserType());
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return loginData.getPassword();
    }

    @Override
    public String getUsername() {
        return loginData.getLogin();
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
