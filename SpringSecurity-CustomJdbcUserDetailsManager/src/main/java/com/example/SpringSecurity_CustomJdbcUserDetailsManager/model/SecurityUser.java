package com.example.SpringSecurity_CustomJdbcUserDetailsManager.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class SecurityUser implements UserDetails {

    private BusinessUser businessUser;

    public SecurityUser(BusinessUser businessUser){
        this.businessUser = businessUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return businessUser.getBusinessUserAuthorities();
    }

    @Override
    public String getPassword() {
        return businessUser.getPassword();
    }

    @Override
    public String getUsername() {
        return businessUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
