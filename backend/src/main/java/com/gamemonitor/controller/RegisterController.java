package com.gamemonitor.controller;

import com.gamemonitor.api.RegisterApi;
import com.gamemonitor.api.model.RegisterUser;
import com.gamemonitor.api.model.User;
import com.gamemonitor.usecase.RegisterUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class RegisterController implements RegisterApi {

    private final RegisterUserUseCase registerUserUseCase;

    @Override
    public ResponseEntity<User> postRegister(RegisterUser registerUser) {
        return ResponseEntity.ok(registerUserUseCase.register(registerUser));
    }
}
