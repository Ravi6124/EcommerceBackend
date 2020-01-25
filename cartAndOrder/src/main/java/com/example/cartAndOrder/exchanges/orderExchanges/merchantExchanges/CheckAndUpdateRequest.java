package com.example.cartAndOrder.exchanges.orderExchanges.merchantExchanges;


import com.example.cartAndOrder.exchanges.cartExchanges.CartProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CheckAndUpdateRequest {

    List<CartProduct> cartProducts;

}
