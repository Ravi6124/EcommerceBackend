package com.example.Merchant.MicroService.DTO;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductDTO
{
    
    private String productId;
    private double defaultPrice;
    private int totalStock;
    private String categoryId;
    private String productName;
    private String description;
    private String imageURL;
    private String defaultMerchantId;

}