package com.gamemonitor.controller;

import com.gamemonitor.api.UserApi;
import com.gamemonitor.api.model.User;
import com.gamemonitor.usecase.GetUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin
public class UserController implements UserApi {

    private final GetUserUseCase getUserUseCase;
    @Override
    public ResponseEntity<User> getUserById(String userId) {
        return ResponseEntity.ok(getUserUseCase.getUserUseCase(userId));
    }

}
