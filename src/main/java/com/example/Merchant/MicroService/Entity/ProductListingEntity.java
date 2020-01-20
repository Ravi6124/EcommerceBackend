package com.example.Merchant.MicroService.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(value="PRODUCTLISTING")
@Getter
@Setter
public class ProductListingEntity
{
    private String productListingId;
    private Map< String,String> attributeMap=new HashMap<>();
    private String merchantId;
    private String productId;
    private Double price;
    private int quantity;
    int productListingRating;
    int numberOfRatings;

}
