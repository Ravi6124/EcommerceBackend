package com.example.cartAndOrder.exchanges.orderExchanges;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CheckOutResponse {

    String orderId;
    boolean status;
    List<UnavailableStock> unavailableStock;

}
