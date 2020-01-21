package com.example.cartAndOrder.services;

import com.example.cartAndOrder.entity.Cart;
import com.example.cartAndOrder.exchanges.CartModifiedResponse;
import com.example.cartAndOrder.exchanges.CartProduct;

public interface CartServices {

     Cart getCart(String userId);

     CartModifiedResponse addItemToCart(String userId, CartProduct cartProduct);

     CartModifiedResponse removeItemFromCart(String userId, String itemId);

     CartModifiedResponse reduceItemFromCart(String userId, String productId);

}
