package com.example.cartAndOrder.exchanges.cartExchanges;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReduceItemFromCartRequest {

    @NotNull
    String userId;

    @NotNull
    String productId;
//
//    @NotNull
//    String quantity;

}
