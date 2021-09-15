package com.nftbazaar.demo.security;

import com.nftbazaar.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private User user;
    private boolean active;

    public MyUserDetails(String username, String password, User user) {
        this.username = username;
        this.password = password;
        this.user = user;
    }

    public MyUserDetails() {
    }

    public MyUserDetails(User user) {
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
