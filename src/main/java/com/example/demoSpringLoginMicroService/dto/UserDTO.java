package com.example.demoSpringLoginMicroService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int userId;
    private String emailAddress;
    private String password;
    private String role;
}
