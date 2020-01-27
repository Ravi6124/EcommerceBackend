package com.example.demoSpringLoginMicroService.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDTO {
    private int userId;
    @NonNull
    private String emailAddress;
    @NotNull
    private String password;
    @NonNull
    private String role;
    private String guestId;
    private String loginSource;
    private String type;
}
