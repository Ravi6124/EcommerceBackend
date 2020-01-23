package com.example.cartAndOrder.controller;


import com.example.cartAndOrder.entity.Cart;
import com.example.cartAndOrder.entity.Order;
import com.example.cartAndOrder.exchanges.CartProduct;
import com.example.cartAndOrder.exchanges.orderExchanges.CheckOutResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.FindOrdersByMidResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.GetOrdersByUserIdRequest;
import com.example.cartAndOrder.exchanges.orderExchanges.GetOrdersByUserIdResponse;
import com.example.cartAndOrder.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServices orderServices;

    @PostMapping("/place")
    ResponseEntity<CheckOutResponse> checkOut(@Valid @RequestBody Cart cart){

        Date date = new Date();
        String userId = cart.getCustomerId();

        if(cart.getItems().size() == 0){
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<CartProduct> cartProducts = cart.getItems();
        double totalAmount = cart.getTotalAmount();

        Order order = new Order();

        order.setItems(cartProducts);
        order.setTotalAmount(totalAmount);
        order.setUserId(userId);
        order.setDate(date);



        return new ResponseEntity<>(orderServices.checkOut(order),HttpStatus.OK);
    }

    @GetMapping("/getByUserId")
    ResponseEntity<GetOrdersByUserIdResponse> getOrdersByUserId(@Valid @RequestBody GetOrdersByUserIdRequest getOrdersByUserIdRequest){

        String userId = getOrdersByUserIdRequest.getUserId();

        return new ResponseEntity<>(orderServices.findOrdersByUserId(userId),HttpStatus.OK);

    }


    @PostMapping("/mail/{email}")
    boolean mail(@PathVariable (value = "email") String email){

        try{
            orderServices.mail(email);
        }catch (Exception e){
            e.printStackTrace();
        }


        return true;
    }

    @GetMapping("/getByMerchantId/{mid}")
    ResponseEntity<List<FindOrdersByMidResponse>> findOrdersByMechantId(@PathVariable(value = "mid") String mid){

        if(mid.equals(null) || mid.equals("")){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(orderServices.findOrdersByMid(mid),HttpStatus.OK);

    }



}
