package com.example.ProductMicroServices.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String productId;
    private double defaultPrice;
    private int totalStock;
    private String categoryId;
    private String productName;
    private String description;
    private String imageURL;
    private String defaultMerchantId;
}
