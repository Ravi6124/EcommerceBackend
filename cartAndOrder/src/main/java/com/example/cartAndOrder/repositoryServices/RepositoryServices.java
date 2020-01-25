package com.example.cartAndOrder.repositoryServices;

import com.example.cartAndOrder.entity.Cart;
import com.example.cartAndOrder.exchanges.cartExchanges.CartProduct;


public interface RepositoryServices {

     Cart findOrCreateCart(String userId);

     Cart addItem(String userId, CartProduct cartProduct);

     Cart removeItem(String userId, String itemId);

     Cart reduceItem(String userId, String productId);

     Cart save (Cart cart);
}
