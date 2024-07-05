package com.gamemonitor.usecase;

import com.gamemonitor.api.model.User;
import com.gamemonitor.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetUserUseCase {

    private final UserService userService;
    private final UserModelMapper userModelMapper;

    public User getUserUseCase(String id) {
        var userDto = userService.getUserById(id);
        return userDto != null ? userModelMapper.mapDtoToUserModel(userDto) : null;
    }
}
