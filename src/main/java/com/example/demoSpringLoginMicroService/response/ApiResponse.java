package com.example.demoSpringLoginMicroService.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private T data;

    public ApiResponse(int statusCode, String messagen) {
        this.statusCode = statusCode;
        this.message = messagen;
    }

    public ApiResponse(T data) {
        this.statusCode = 1000;
        this.message = "SUCCESS";
        this.data = data;
    }
}
