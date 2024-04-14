package com.example.challengeionix.converter;

import com.example.challengeionix.dto.UserRequestDTO;
import com.example.challengeionix.entity.User;
import org.springframework.core.convert.converter.Converter;

public class ConverterFromDTOtoUser implements Converter<UserRequestDTO, User> {
    @Override
    public User convert(UserRequestDTO userDTORequest) {
        User user = new User();
        user.setName(userDTORequest.getName());
        user.setUsername(userDTORequest.getUsername());
        user.setEmail(userDTORequest.getEmail());
        user.setPhone(userDTORequest.getPhone());
        return user;
    }
}
