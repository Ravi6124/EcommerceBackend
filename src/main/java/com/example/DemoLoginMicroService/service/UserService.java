package com.example.DemoLoginMicroService.service;

import com.example.DemoLoginMicroService.dto.UserDTO;
import com.example.DemoLoginMicroService.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
    public PasswordEncoder encoder();
    User registerUser(UserDTO userDTO);
    Iterable<User> findAll();

    boolean login(UserDTO userDTO);
}
