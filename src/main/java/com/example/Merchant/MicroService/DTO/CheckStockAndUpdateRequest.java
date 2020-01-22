package com.example.Merchant.MicroService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckStockAndUpdateRequest {

    String productId;
    String merchantId;
    int quantity;
}
