package com.example.Merchant.MicroService.DTO;

import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductListingDTO
{
    String productListingId;

    String color;
    String size;
    String theme;

    String merchantId;
    String productId;
    String productName;
    String productDescription;
    String productImageURL;
    Double price;
    int quantity;
    double productListingRating;
    int numberOfRatings;
    List<ProductListingEntity> orderList;


}
