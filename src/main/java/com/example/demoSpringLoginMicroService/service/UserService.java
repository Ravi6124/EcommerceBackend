package com.example.demoSpringLoginMicroService.service;

import com.example.demoSpringLoginMicroService.dto.UserDTO;
import com.example.demoSpringLoginMicroService.entity.User;

public interface UserService {
    public User save(User user);
    public User findUser(User user);

    public User checkEmailExists(String emailAddress,String role);
}
