package com.example.cartAndOrder.exchanges;

import com.example.cartAndOrder.entity.Cart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartModifiedResponse {

    private Cart cart;
    private int resultCode;

}
