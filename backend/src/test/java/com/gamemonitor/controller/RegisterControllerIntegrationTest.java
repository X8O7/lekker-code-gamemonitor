package com.gamemonitor.controller;

import com.gamemonitor.GameMonitorApplication;
import com.gamemonitor.api.model.RegisterUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({ SpringExtension.class })
@SpringBootTest(classes = GameMonitorApplication.class)
class RegisterControllerIntegrationTest {
    private MockMvc mockMvc;

    @Autowired
    private RegisterController registerController;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void shouldPostRegisterAndReturnUserModel() throws Exception {

        this.mockMvc.perform(
                post("/register", new RegisterUser()
                        .name("John")
                        .email("john@test.de")
                        .password("123456")))
                .andExpect(status().isOk());
    }
}