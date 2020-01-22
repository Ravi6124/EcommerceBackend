package com.example.demoSpringLoginMicroService.controller;

import com.example.demoSpringLoginMicroService.config.JwtUtil;
import com.example.demoSpringLoginMicroService.dto.UserDTO;
import com.example.demoSpringLoginMicroService.entity.User;
import com.example.demoSpringLoginMicroService.response.ApiResponse;
import com.example.demoSpringLoginMicroService.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    private
    JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> userLogin(@RequestBody UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user = userService.findUser(user);
        if (null != user) {
            String accessToken = jwtUtil.generateToken(user);
            return new ResponseEntity<>(new ApiResponse<>(accessToken), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(900, "Invalid Credentials"), HttpStatus.OK);
    }
}
