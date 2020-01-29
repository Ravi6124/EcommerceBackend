package com.example.Merchant.MicroService.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="PRODUCTLISTING")
@Getter
@Setter
public class  ProductListingEntity
{
    @Id
    private String productListingId;

    private String color;
    private String size;
    private String theme;

    private String categoryId;
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
