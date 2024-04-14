package com.example.challengeionix.controller;

import com.example.challengeionix.converter.ConverterFromDTOtoUser;
import com.example.challengeionix.converter.ConverterFromUserToDTO;
import com.example.challengeionix.datosmock.Datos;
import com.example.challengeionix.service.Impl.ConsumingExternalApiImpl;
import com.example.challengeionix.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    ConverterFromUserToDTO converterFromUserToDTO;

    @Mock
    ConverterFromDTOtoUser converterFromDTOtoUser;


    @MockBean
    UserServiceImpl userService;

    @MockBean
    ConsumingExternalApiImpl consumingExternalApi;

    @Nested
    @DisplayName("Controller User")
    class UserServiceImplTest {

        @Test
        @DisplayName("Controller User - all user ")
        void getAllUsers() throws Exception {

            when(userService.getAllUsers()).thenReturn(Datos.DATOS_USERS_RESPONSE);
            mockMvc.perform(get("/api/v1/user/all"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

        }

        @Test
        @DisplayName("Controller User - all by email ")
        void getUsersByEmail() throws Exception {

            when(userService.getUserByEmail("pedro@gmail.com")).thenReturn(Datos.getUserToDto());
            mockMvc.perform(get("/user/byEmail/pedro@gmail.com"))
                    .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                    .andReturn();

        }

    }

}
