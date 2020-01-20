package com.example.DemoLoginMicroService.service.impl;

import com.example.DemoLoginMicroService.dto.UserDTO;
import com.example.DemoLoginMicroService.entity.User;
import com.example.DemoLoginMicroService.repository.UserRepository;
import com.example.DemoLoginMicroService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
     PasswordEncoder passwordEncoder;

    @Autowired
     UserRepository userRepository;

    @Autowired
     RedisTemplate redisTemplate;

    @Override
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User registerUser(UserDTO userDTO) {
       User user=new User();

       user.setEmailAddress(userDTO.getEmailAddress());
       user.setToken(passwordEncoder.encode(userDTO.getPassword()));
       user.setRole(userDTO.getRole());

       return userRepository.save(user);

    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Cacheable(value = "email", key = "#userDTO.emailAddress")
    public boolean login(UserDTO userDTO) {
        User user = userRepository.findByEmailAddress(userDTO.getEmailAddress()).get();
        if(user!=null)
        {
            String userToken=passwordEncoder.encode(userDTO.getPassword());
            if(userToken==user.getToken())
                return true;
            else
                return false;
        }
       else
           return false;
    }



}
