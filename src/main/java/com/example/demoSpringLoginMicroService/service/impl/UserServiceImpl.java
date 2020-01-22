package com.example.demoSpringLoginMicroService.service.impl;

import com.example.demoSpringLoginMicroService.dto.UserDTO;
import com.example.demoSpringLoginMicroService.entity.User;
import com.example.demoSpringLoginMicroService.repository.UserRepository;
import com.example.demoSpringLoginMicroService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUser(User user) {
        Optional<User> userExists=userRepository.findByEmailAddress(user.getEmailAddress());
        if(userExists.isPresent()) {
            String userPasswordExists=String.valueOf(userExists.get().getPassword());
            String userPassword=String.valueOf(user.getPassword());
            return userExists.get();
        }
        else
            return null;
    }
}
