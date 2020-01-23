package com.example.cartAndOrder.exchanges.orderExchanges;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnavailableStock {

    String productName;
    int stock;
}
