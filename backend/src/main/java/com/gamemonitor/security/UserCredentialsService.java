package com.gamemonitor.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserCredentialsService {
    UserDetailsService userDetailsService();
}
