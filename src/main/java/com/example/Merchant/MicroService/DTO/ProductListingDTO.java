package com.example.Merchant.MicroService.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ProductListingDTO
{
    String productListingId;
    Map< String,String> hm = new HashMap<>();
    String merchantId;
    String productId;
    Double price;
    int quantity;
    int productListingRating;
    int numberOfRatings;

}
