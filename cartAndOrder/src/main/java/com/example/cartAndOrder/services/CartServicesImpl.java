package com.example.cartAndOrder.services;

import com.example.cartAndOrder.entity.Cart;
import com.example.cartAndOrder.exchanges.CartModifiedResponse;
import com.example.cartAndOrder.exchanges.CartProduct;
import com.example.cartAndOrder.repositoryServices.RepositoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CartServicesImpl implements CartServices {

    @Autowired
    RepositoryServices repositoryServices;


    @Override
    public Cart getCart(String userId) {
        Cart cart = repositoryServices.findOrCreateCart(userId);

        //System.out.println(cart.getCustomerId());
        double totalamount = 0;

        if(cart.getItems().size() > 0) {
            List<CartProduct> list = cart.getItems();

            Iterator<CartProduct> iterator = list.iterator();



            while (iterator.hasNext()) {
                CartProduct cartProduct = iterator.next();
                totalamount += cartProduct.getPrice() * cartProduct.getQuantity();
            }

            cart.setTotalAmount(totalamount);
            repositoryServices.save(cart);
        }

        return cart;
    }

    @Override
    public CartModifiedResponse addItemToCart(String userId, CartProduct cartProduct) {
        Cart cart = repositoryServices.addItem(userId,cartProduct);

        CartModifiedResponse cartModifiedResponse = new CartModifiedResponse();
        cartModifiedResponse.setCart(cart);
        cartModifiedResponse.setResultCode(100);

        return  cartModifiedResponse;
    }

    @Override
    public CartModifiedResponse removeItemFromCart(String userId, String itemId) {

        Cart cart = repositoryServices.removeItem(userId,itemId);

        CartModifiedResponse response = new CartModifiedResponse();

        response.setCart(cart);
        response.setResultCode(100);

        return response;


    }

    @Override
    public CartModifiedResponse reduceItemFromCart(String userId, String productId) {

        Cart cart = repositoryServices.reduceItem(userId,productId);
        CartModifiedResponse response = new CartModifiedResponse();

        response.setCart(cart);
        response.setResultCode(100);

        return  response;
    }


}
