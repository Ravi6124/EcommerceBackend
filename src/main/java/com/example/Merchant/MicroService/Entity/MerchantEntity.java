package com.example.Merchant.MicroService.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(value="MERCHANT")
@Getter
@Setter
public class MerchantEntity
{
    @Id
    private String merchantId;
    private String email;
    private String firstName;
    private String lastName;
    private String contactNumber;
    //private String password;
    private String city;
    private String imageURL;
    private double merchantRating;
    private int numberOfMerchantRatings;
    private int totalProductSold;
    private List<ProductListingEntity> orderList;
}
