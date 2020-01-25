package com.example.demoSpringLoginMicroService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    String email;
    String firstName;
    String lastName;
    String contactNumber;
    String address;
    String customerId;
    String profileURL;
}
