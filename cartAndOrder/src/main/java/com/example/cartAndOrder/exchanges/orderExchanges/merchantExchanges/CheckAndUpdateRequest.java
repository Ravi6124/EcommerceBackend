package com.example.cartAndOrder.exchanges.orderExchanges.merchantExchanges;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckAndUpdateRequest {

    String productId;
    String merchantId;
    int quantity;
}
