package com.example.DemoLoginMicroService.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class UserDTO {

    private int userId;

    private String emailAddress;
    private String password;
    private String confirmPassword;

    private String role;
}
