package com.example.demoSpringLoginMicroService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String accessToken;
    private String role;
    private String guestId;
}
