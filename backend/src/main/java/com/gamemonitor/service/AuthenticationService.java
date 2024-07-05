package com.gamemonitor.service;

import com.gamemonitor.domain.dto.CredentialsDTO;
import com.gamemonitor.domain.dto.RegisterUserDTO;
import com.gamemonitor.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    public UserDTO signup(RegisterUserDTO registerUserDTO) {
        return userService.createUser(registerUserDTO);
    }

    public CredentialsDTO authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        return userService.getUserByEmail(email);
    }
}
