package com.example.challengeionix.controller;


import com.example.challengeionix.dto.UserRequestDTO;
import com.example.challengeionix.dto.UserResponseDTO;
import com.example.challengeionix.response.ResponseHandler;
import com.example.challengeionix.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    @Autowired
    private IUserService userService;


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        StopWatch watch = new StopWatch();
        watch.start();

        if (userService.userExists(userRequestDTO.getUsername())) {
            return ResponseHandler.generateResponse("Username already exists!", HttpStatus.BAD_REQUEST, String.valueOf(watch.getTime()), null, 0 );
        }

        if (userService.emailExists(userRequestDTO.getEmail())) {
            return ResponseHandler.generateResponse("Email address already exists.", HttpStatus.BAD_REQUEST, String.valueOf(watch.getTime()), null, 0 );
        }
        UserResponseDTO userDto = userService.createUser(userRequestDTO);
        watch.stop();
        return ResponseHandler.generateResponse("User created successfully!", HttpStatus.CREATED, String.valueOf(watch.getTime()), userDto, 1 );
    }



    @GetMapping("/byEmail/{email}")
    public ResponseEntity<?> getUserByEmailAddress(@PathVariable(value = "email") String email) throws IOException {

        StopWatch watch = new StopWatch();
        watch.start();

        // without this condition, service layer will avoid the operation for any invalid email.
        if (!userService.emailExists(email)) {
            return ResponseHandler.generateResponse("Email don't exists!!", HttpStatus.BAD_REQUEST, String.valueOf(watch.getTime()), null, 0 );
        }
        UserResponseDTO userDto = userService.getUserByEmail(email);
        watch.stop();
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, String.valueOf(watch.getTime()), userDto, 1 );

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() throws IOException {

        StopWatch watch = new StopWatch();
        watch.start();

        if (userService.getAllUsers().size() < 1) {
            return ResponseHandler.generateResponse("No data found.", HttpStatus.NO_CONTENT, String.valueOf(watch.getTime()), null, userService.getAllUsers().size() );

        }
        List<UserResponseDTO> userResponseDTOS = userService.getAllUsers();
        watch.stop();
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, String.valueOf(watch.getTime()), userResponseDTOS, userService.getAllUsers().size() );
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> eliminarComentario(@PathVariable(value = "id") Long id) throws IOException {

        // without this condition, service layer will avoid the operation for any invalid id.
        if (!userService.userExistsById(id)) {
            return new ResponseEntity<>("User no exists", HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        return  new ResponseEntity<>("User deleted.", HttpStatus.OK);
    }

}
