package com.example.challengeionix.datosmock;

import com.example.challengeionix.dto.UserRequestDTO;
import com.example.challengeionix.dto.UserResponseDTO;
import com.example.challengeionix.entity.User;


import java.util.Arrays;
import java.util.List;

public class Datos {

    public final static  List<User> DATOS_USERS = Arrays.asList(
            new User(1L,"pedro", "pedro001","pedro@gmail.com","0424859485"),
            new User(2L, "maria", "maria001","maria@gmail.com","0424888412"),
            new User(3L, "jose", "jose001","jose@gmail.com","0424859698"));


    public final static  List<UserResponseDTO> DATOS_USERS_RESPONSE = Arrays.asList(
            new UserResponseDTO(1L,"pedro", "pedro001","pedro@gmail.com","0424859485"),
            new UserResponseDTO(2L, "maria", "maria001","maria@gmail.com","0424888412"),
            new UserResponseDTO(3L, "jose", "jose001","jose@gmail.com","0424859698"));


    public final static UserResponseDTO getUserToDto(){
        return new UserResponseDTO(1L,"pedro", "pedro001","pedro@gmail.com","0424859485");
    }
    public final static UserRequestDTO getUseRequestDTO(){
        return new UserRequestDTO("pedro", "pedro001","pedro@gmail.com","0424859485");
    }
    public final static User getDtoToUser(){
        return new User(1L,"pedro", "pedro001","pedro@gmail.com","0424859485");
    }

}
