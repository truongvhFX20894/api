package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MyUserDetail implements UserDetails {
    private Users user;

    public MyUserDetail(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Roles roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

//        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(roles.getName()));
//        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return user.getIsActive()==1;
    }
}
