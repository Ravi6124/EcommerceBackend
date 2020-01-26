package com.example.demoSpringLoginMicroService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginHistoryDTO {

    private int loginId;
    private int userId;
    private String loginTime;
    private String loginSource;
    private String type;
}
