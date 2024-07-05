package com.gamemonitor.usecase;

import com.gamemonitor.api.model.Credentials;
import com.gamemonitor.api.model.User;
import com.gamemonitor.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AuthenticationUseCase {

    private final AuthenticationService authenticationService;
    private final UserModelMapper userModelMapper;

    public User authenticate(Credentials credentials) {
        var credentialsDTO = authenticationService.authenticate(credentials.getEmail(), credentials.getPassword());
        return userModelMapper.mapDtoToUserModel(credentialsDTO.user());
    }
}
