package com.example.demoSpringLoginMicroService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantDTO {
    private String merchantId;
    private String email;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String password;
    private String city;
    private String imageURL;
    private double merchantRating;
    private int numberOfMerchantRatings;
    int totalProductSold;
}
