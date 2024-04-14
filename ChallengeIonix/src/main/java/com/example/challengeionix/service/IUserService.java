package com.example.challengeionix.service;

import com.example.challengeionix.dto.UserRequestDTO;
import com.example.challengeionix.dto.UserResponseDTO;

import java.util.List;

public interface IUserService {

   List<UserResponseDTO> getAllUsers();
   UserResponseDTO getUserByEmail(String email);
   UserResponseDTO createUser(UserRequestDTO userDTO);
   void deleteUser(Long id);
   boolean userExists(String username);
   boolean emailExists(String email);
   boolean userExistsById(Long id);
}
