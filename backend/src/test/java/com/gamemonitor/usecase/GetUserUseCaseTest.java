package com.gamemonitor.usecase;

import com.gamemonitor.domain.dto.UserDTO;
import com.gamemonitor.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringJUnitConfig
class GetUserUseCaseTest {

    @MockBean
    private UserService userService;

    @Test
    void shouldGetUserUseCase() {
        //given
        var userDto = new UserDTO(23L, "Jane", 4, 99999);

        //when
        Mockito.when(userService.getUserById(ArgumentMatchers.anyString())).thenReturn(userDto);

        //then
        var response = new GetUserUseCase(userService, new UserModelMapper()).getUserUseCase("23");

        assertNotNull(response);
        assertEquals(userDto.id(), Long.valueOf(response.getId()));
        assertEquals(userDto.name(), response.getName());
        assertEquals(userDto.rank(), response.getRank());
        assertEquals(userDto.score(), response.getScore());
    }
    @Test
    void shouldGetUserUseCaseAndReturnNull() {
        //given

        //when
        Mockito.when(userService.getUserById(ArgumentMatchers.anyString())).thenReturn(null);

        //then
        var response = new GetUserUseCase(userService, new UserModelMapper()).getUserUseCase("44");

        assertNull(response);
    }
}