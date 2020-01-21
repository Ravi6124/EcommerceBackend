package com.example.cartAndOrder.exchanges;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartProduct {

    private String productId;
    private String productName;
    private String imageURL;
    private String merchantId;
    private int quantity;
    private double price;
}
