package com.seiferson.hxtask.config.security;

import com.seiferson.hxtask.model.security.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Account account;

    public CustomUserDetails(Account account) {
        this.account = account;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getNickname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return account.getEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.getEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return account.getEnabled();
    }

    @Override
    public boolean isEnabled() {
        return account.getEnabled();
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = account.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        return authorities;
    }
}
