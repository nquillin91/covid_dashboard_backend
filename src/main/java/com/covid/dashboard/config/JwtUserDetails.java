package com.covid.dashboard.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtUserDetails 
        extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = -6411988532329234916L;

    public JwtUserDetails(String username, String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}