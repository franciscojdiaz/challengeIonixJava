package com.example.challengeionix.serviceImpl;

import com.example.challengeionix.converter.ConverterFromDTOtoUser;
import com.example.challengeionix.converter.ConverterFromUserToDTO;
import com.example.challengeionix.datosmock.Datos;
import com.example.challengeionix.dto.UserResponseDTO;
import com.example.challengeionix.entity.User;
import com.example.challengeionix.repository.UserRepository;
import com.example.challengeionix.service.Impl.ConsumingExternalApiImpl;
import com.example.challengeionix.service.Impl.CryptoServiceImpl;
import com.example.challengeionix.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.challengeionix.datosmock.Datos.getDtoToUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
public class ServiceImplTest {


    @Mock
    UserRepository userRepository;

    @Mock
    ConverterFromUserToDTO converterFromUserToDTO;

    @Mock
    ConverterFromDTOtoUser converterFromDTOtoUser;

    @InjectMocks
    UserServiceImpl userService;
    @InjectMocks
    CryptoServiceImpl cryptoService;


    @InjectMocks
    ConsumingExternalApiImpl consumingExternalApi;

    @BeforeEach
    public void setUp() {
        Mockito.framework().clearInlineMocks();
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @DisplayName("Services User")
    class UserServiceImplTest {

        @Test
        @DisplayName("Services User - Testing get user by email ")
        void getUserByEmail(){

            when(userRepository.findByEmail("pedro@gmail.com")).thenReturn(Optional.of(Datos.getDtoToUser()));
            when(converterFromDTOtoUser.convert(any())).thenReturn(getDtoToUser());
            when(converterFromUserToDTO.convert(any())).thenReturn(Datos.getUserToDto());
            UserResponseDTO responseDTO = userService.getUserByEmail("pedro@gmail.com");
            //Then
            assertNotNull(responseDTO);

        }

        @Test
        @DisplayName("Services User - Testing get all user ")
        void getAllUsers(){
            List<User> datos = Collections.emptyList();
            when(userRepository.findAll()).thenReturn(Datos.DATOS_USERS);
            when(converterFromDTOtoUser.convert(any())).thenReturn(getDtoToUser());
            when(converterFromUserToDTO.convert(any())).thenReturn(Datos.getUserToDto());
            List<UserResponseDTO> userResponseDTOList = userService.getAllUsers();
            verify(userRepository).findAll();
            assertThat(userResponseDTOList.size()).isGreaterThan(1);
        }

        @Test
        @DisplayName("Services User - Testing create user ")
        void createUser(){
            //Given
            when(userRepository.save(any())).thenReturn(Datos.getDtoToUser());
            when(converterFromDTOtoUser.convert(any())).thenReturn(getDtoToUser());
            when(converterFromUserToDTO.convert(any())).thenReturn(Datos.getUserToDto());
            //Whean
            UserResponseDTO responseDTO = userService.createUser(Datos.getUseRequestDTO());

            //Then
            assertNotNull(responseDTO);
            assertEquals("pedro@gmail.com", Datos.getUseRequestDTO().getEmail());
            verify(userRepository).save(any());
        }

        @Test
        @DisplayName("Services User - Testing delete user ")
        void deleteUser(){
            userRepository.deleteById(any());

        }

    }

    @Nested
    @DisplayName("Services Crypto")
    class cryptoParamTest {


        @Test
        @DisplayName("Services Crypto - Testing crypto ")
        void cryptoParam(){
            String result = cryptoService.cryptoParam("1-9");
            assertNotNull(result);
        }
    }



    }
