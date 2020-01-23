package com.example.cartAndOrder.exchanges.orderExchanges.merchantExchanges;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckAndUpdateResponse {
    boolean status;
    int quantity;
}
