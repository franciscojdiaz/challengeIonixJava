package com.example.challengeionix.converter;

import com.example.challengeionix.dto.UserRequestDTO;
import com.example.challengeionix.dto.UserResponseDTO;
import com.example.challengeionix.entity.User;
import org.springframework.core.convert.converter.Converter;

public class ConverterFromUserToDTO implements Converter<User, UserResponseDTO> {

    @Override
    public UserResponseDTO convert(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setPhone(user.getPhone());
        return userResponseDTO;
    }
}
