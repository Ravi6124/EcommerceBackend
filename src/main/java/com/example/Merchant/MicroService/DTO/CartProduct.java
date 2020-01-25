package com.example.Merchant.MicroService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {
    private String productId;
    private String productName;
    private String imageURL;
    private String merchantId;
    private int quantity;
    private double price;
}
