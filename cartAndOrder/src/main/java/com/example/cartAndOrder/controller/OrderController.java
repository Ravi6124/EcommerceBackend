package com.example.cartAndOrder.controller;


import com.example.cartAndOrder.entity.Cart;
import com.example.cartAndOrder.entity.Order;
import com.example.cartAndOrder.exchanges.orderExchanges.CheckOutResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.FindOrdersByMidResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.GetOrdersByUserIdRequest;
import com.example.cartAndOrder.exchanges.orderExchanges.GetOrdersByUserIdResponse;
import com.example.cartAndOrder.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@EnableAsync
@RequestMapping("/order")
@CrossOrigin(allowedHeaders = "*", origins = "*")
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

        Order order = new Order();

        order.setItems(cart.getItems());
        order.setTotalAmount(cart.getTotalAmount());
        order.setUserId(cart.getCustomerId());
        order.setDate(date);

        return new ResponseEntity<>(orderServices.checkOut(order,cart),HttpStatus.OK);
    }

    @GetMapping("/getByUserId")
    ResponseEntity<GetOrdersByUserIdResponse> getOrdersByUserId(@RequestParam String userId){

        //String userId = getOrdersByUserIdRequest.getUserId();

        return new ResponseEntity<>(orderServices.findOrdersByUserId(userId),HttpStatus.OK);

    }


    //email checking API

//
//    @PostMapping("/mail/{email}")
//    boolean mail(@PathVariable (value = "email") String email){
//
//        try{
//            orderServices.mail(email);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        return true;
//    }

    @GetMapping("/getByMerchantId/{mid}")
    ResponseEntity<List<FindOrdersByMidResponse>> findOrdersByMechantId(@PathVariable(value = "mid") String mid){

        if(mid.equals(null) || mid.equals("")){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(orderServices.findOrdersByMid(mid),HttpStatus.OK);

    }



}
