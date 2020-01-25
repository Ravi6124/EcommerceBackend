package com.example.demoSpringLoginMicroService.config;

import com.example.demoSpringLoginMicroService.config.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordEncoder {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
