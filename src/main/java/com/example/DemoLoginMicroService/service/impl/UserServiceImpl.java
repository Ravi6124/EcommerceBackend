package com.example.DemoLoginMicroService.service.impl;

import com.example.DemoLoginMicroService.dto.UserDTO;
import com.example.DemoLoginMicroService.entity.User;
import com.example.DemoLoginMicroService.repository.UserRepository;
import com.example.DemoLoginMicroService.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
     PasswordEncoder passwordEncoder;

    @Autowired
     UserRepository userRepository;

    @Override
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User registerUser(UserDTO userDTO) {
       User user=new User();

       user.setEmailAddress(userDTO.getEmailAddress());
       user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
       user.setRole(userDTO.getRole());

       return userRepository.save(user);

    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

}
