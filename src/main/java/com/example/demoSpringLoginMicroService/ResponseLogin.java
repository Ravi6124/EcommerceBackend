package com.example.demoSpringLoginMicroService;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLogin {

    String userId;
    String accessTokeen;
    int statusCode;

}
