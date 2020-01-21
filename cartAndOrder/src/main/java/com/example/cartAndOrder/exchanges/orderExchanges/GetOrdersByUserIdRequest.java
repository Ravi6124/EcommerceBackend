package com.example.cartAndOrder.exchanges.orderExchanges;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetOrdersByUserIdRequest {

    @NotNull
    String userId;
}
