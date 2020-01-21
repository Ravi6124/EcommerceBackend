package com.example.cartAndOrder.controller;


import com.example.cartAndOrder.entity.Cart;
import com.example.cartAndOrder.exchanges.*;
import com.example.cartAndOrder.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServices cartServices;

    @GetMapping("/cart")
    ResponseEntity<Cart> getCart(@Valid @RequestBody GetCartRequest getCartRequest){

        String userId = getCartRequest.getUserId();
        Cart response = cartServices.getCart(userId);

        return new ResponseEntity<Cart>(response,HttpStatus.ACCEPTED);

    }


    @PostMapping("/item")
    ResponseEntity<CartModifiedResponse> addItemToCart(@Valid @RequestBody AddItemToCartRequest addItemToCartRequest){

        String userId = addItemToCartRequest.getUserId();
        CartProduct cartProduct = addItemToCartRequest.getCartProduct();
        String quantity = "1";



        int qty = addItemToCartRequest.getCartProduct().getQuantity();

        if(qty<0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<CartModifiedResponse>(cartServices.addItemToCart(userId,cartProduct),HttpStatus.OK);

    }

    @DeleteMapping("/item")
    ResponseEntity<CartModifiedResponse> removeItemFromCart(@Valid @RequestBody RemoveItemFromCartRequest removeItemFromCartRequest){

        String userId = removeItemFromCartRequest.getUserId();
        String pid = removeItemFromCartRequest.getProductId();

        return new ResponseEntity<CartModifiedResponse>(cartServices.removeItemFromCart(userId,pid),HttpStatus.OK);

    }

    @DeleteMapping("/reduceitem")
    ResponseEntity<CartModifiedResponse> reduceItemFromCart(@Valid @RequestBody ReduceItemFromCartRequest reduceItemFromCartRequest){

        String userId = reduceItemFromCartRequest.getUserId();
        String productId = reduceItemFromCartRequest.getProductId();



        return new ResponseEntity<>(cartServices.reduceItemFromCart(userId,productId),HttpStatus.OK);
    }





}
