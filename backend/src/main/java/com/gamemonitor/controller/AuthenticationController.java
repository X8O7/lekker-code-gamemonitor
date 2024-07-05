package com.gamemonitor.controller;

import com.gamemonitor.api.AuthenticateApi;
import com.gamemonitor.api.model.Credentials;
import com.gamemonitor.api.model.User;
import com.gamemonitor.usecase.AuthenticationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin
public class AuthenticationController implements AuthenticateApi {

    private final AuthenticationUseCase authenticationUseCase;

    @Override
    public ResponseEntity<User> postAuthenticate(Credentials credentials) {
        return ResponseEntity.ok(authenticationUseCase.authenticate(credentials));
    }
}
