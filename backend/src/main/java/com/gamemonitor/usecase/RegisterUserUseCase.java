package com.gamemonitor.usecase;

import com.gamemonitor.api.model.RegisterUser;
import com.gamemonitor.api.model.User;
import com.gamemonitor.domain.dto.RegisterUserDTO;
import com.gamemonitor.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RegisterUserUseCase {
    private final UserService userService;
    private final UserModelMapper userModelMapper;

    public User register(RegisterUser registerUser) {
        var registerDto = new RegisterUserDTO(registerUser.getName(), registerUser.getEmail(), registerUser.getPassword());
        var userDto = userService.createUser(registerDto);

        return userModelMapper.mapDtoToUserModel(userDto);
    }
}
