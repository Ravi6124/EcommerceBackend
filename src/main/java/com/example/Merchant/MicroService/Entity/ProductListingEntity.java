package com.example.Merchant.MicroService.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="PRODUCTLISTING")
@Getter
@Setter
public class ProductListingEntity
{
    private String productListingId;

    private String color;
    private String size;
    private String theme;

    private String merchantId;
    private String productId;
    private String productName;
    private String productDescription;
    private String productImageURL;
    private Double price;
    private int quantity;
    private double productListingRating;
    private int numberOfRatings;

}
