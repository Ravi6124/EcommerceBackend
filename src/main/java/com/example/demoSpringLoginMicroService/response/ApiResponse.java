package com.example.demoSpringLoginMicroService.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    private int statusCode;
    private String message;
    private String accessToken;
    private String userId;
    private String guestId;
    private String emailAddress;

    public ApiResponse(int statusCode, String accessToken, String userId, String emailAddress) {
        this.statusCode = statusCode;
        this.accessToken = accessToken;
        this.userId = userId;
        this.emailAddress = emailAddress;
    }

    public ApiResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ApiResponse(String guestId) {
        this.guestId = guestId;
    }
}
