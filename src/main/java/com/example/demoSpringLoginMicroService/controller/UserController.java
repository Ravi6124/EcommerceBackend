package com.example.demoSpringLoginMicroService.controller;

import com.example.demoSpringLoginMicroService.dto.UserDTO;
import com.example.demoSpringLoginMicroService.entity.User;
import com.example.demoSpringLoginMicroService.response.ApiResponse;
import com.example.demoSpringLoginMicroService.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/customer")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> saveUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        boolean emailExists = userService.checkEmailExists(userDTO.getEmailAddress(), userDTO.getRole());
        System.out.println("status :" + emailExists);
        if (emailExists) {
            return new ResponseEntity<>(new ApiResponse<>(800, "Email Address with the specified role already exists"), HttpStatus.OK);
        } else {
            User userCreated = userService.save(user);
            return new ResponseEntity<>(new ApiResponse<>(1000, "User successfully registered"), HttpStatus.OK);

        }

    }
}
