package com.gamemonitor.usecase;

import com.gamemonitor.api.model.User;
import com.gamemonitor.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class UserModelMapper {
    public User mapDtoToUserModel(UserDTO userDTO) {
        return new User()
                .id(userDTO.id().intValue())
                .name(userDTO.name())
                .rank(userDTO.rank())
                .score(userDTO.score());
    }
}
