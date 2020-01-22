package com.example.ProductMicroServices.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductMerchant {
    String productId;
    String defaultMerchantId;
    double defaultPrice;
}
