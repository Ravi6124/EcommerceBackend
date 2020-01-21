package com.example.Merchant.MicroService.DTO;

import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
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
    double productListingRating;
    int numberOfRatings;
    List<ProductListingEntity> orderList;

}
