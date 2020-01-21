package com.example.cartAndOrder.exchanges;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddItemToCartRequest {

    @NotNull
    String userId;

    @NotNull
    CartProduct cartProduct;


}
