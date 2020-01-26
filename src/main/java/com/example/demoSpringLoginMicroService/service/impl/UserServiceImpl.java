package com.example.demoSpringLoginMicroService.service.impl;

import com.example.demoSpringLoginMicroService.config.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUser(User user) {
        Optional<User> userExists = userRepository.findByEmailAddressAndRole(user.getEmailAddress(),user.getRole());

        if (userExists.isPresent()) {
            String userPasswordExists = String.valueOf(userExists.get().getPassword());
            String userPassword = String.valueOf(user.getPassword());
            return userExists.get();
        } else
            return null;
    }

    @Override
    public User checkEmailExists(String emailAddress, String role) {
        Optional<User> userEmailExists = userRepository.findByEmailAddressAndRole(emailAddress,role);
        if (userEmailExists.isPresent()) {
                return userEmailExists.get();
        }
        return null;
    }
}
