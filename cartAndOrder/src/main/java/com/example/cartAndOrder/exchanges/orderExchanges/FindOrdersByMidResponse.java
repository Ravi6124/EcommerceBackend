package com.example.cartAndOrder.exchanges.orderExchanges;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FindOrdersByMidResponse {

    String productName;
    String customerId;
    String imageURL;
    int quantity;
    Date orderDate;
    double price;
}
