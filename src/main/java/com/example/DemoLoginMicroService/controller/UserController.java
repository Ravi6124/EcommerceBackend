package com.example.DemoLoginMicroService.controller;

import com.example.DemoLoginMicroService.dto.UserDTO;
import com.example.DemoLoginMicroService.entity.User;

import com.example.DemoLoginMicroService.repository.UserRepository;
import com.example.DemoLoginMicroService.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/userCreate")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> addOrUpdateUser(@RequestBody UserDTO userDTO)
    {
        User userCreated=userService.registerUser(userDTO);
        return new ResponseEntity<String>("created",HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> findAllUser(){
//        List<User> userList = new ArrayList<>();
//        userService.findAll().forEach(user -> {
//            userList.add(user);
//        });

       return (ArrayList<User>)userRepository.findAll();


        //return userList;
    }
}
