package com.example.Merchant.MicroService.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductMerchant
{
    String productId;
    String defaultMerchantId;
    double defaultPrice;
}

