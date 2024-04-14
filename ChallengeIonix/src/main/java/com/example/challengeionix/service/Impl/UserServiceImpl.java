package com.example.challengeionix.service.Impl;

import com.example.challengeionix.converter.ConverterFromDTOtoUser;
import com.example.challengeionix.converter.ConverterFromUserToDTO;
import com.example.challengeionix.dto.UserRequestDTO;
import com.example.challengeionix.dto.UserResponseDTO;
import com.example.challengeionix.entity.User;
import com.example.challengeionix.exception.ResourceNotFoundException;
import com.example.challengeionix.repository.UserRepository;
import com.example.challengeionix.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDTO> getAllUsers() {

        // converter to users
        Converter<User, UserResponseDTO> converterToResponseDTO = new ConverterFromUserToDTO();
        List<User> userList = userRepository.findAll();
        return userList.stream().parallel().map(user -> converterToResponseDTO.convert(user)).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        Converter<User, UserResponseDTO> converterToResponseDTO = new ConverterFromUserToDTO();
        Optional<User> userOpt = Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("getUserByEmail", "Email don't exists!! : " + email, 1)));
        return converterToResponseDTO.convert(userOpt.get());
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // converter user to save
        Converter<UserRequestDTO, User> converterToUser = new ConverterFromDTOtoUser();
        Converter<User, UserResponseDTO> converterToResponseDTO = new ConverterFromUserToDTO();
        User user = converterToUser.convert(userRequestDTO);
        return converterToResponseDTO.convert(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> userOpt = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("deleteUser", "Id don't exists!! : " + id, 1)));
        userRepository.deleteById(userOpt.get().getId());
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean userExistsById(Long id) {
        return userRepository.existsByIdIs(id);
    }

}
