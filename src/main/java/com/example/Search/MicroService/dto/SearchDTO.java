package com.example.Search.MicroService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchDTO
{


    private String productListingId;

    private String color;
    private String size;
    private String theme;

    private String merchantId;
    private String productId;
    private String productName;
    private String description;
    private String imageURL;
    private Double price;
    private int quantity;
    private double productListingRating;
    private int numberOfRatings;


}
