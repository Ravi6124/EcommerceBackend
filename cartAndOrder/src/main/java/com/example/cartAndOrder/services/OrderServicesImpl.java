package com.example.cartAndOrder.services;


import com.example.cartAndOrder.entity.Cart;
import com.example.cartAndOrder.entity.Order;
import com.example.cartAndOrder.exchanges.CartProduct;
import com.example.cartAndOrder.exchanges.orderExchanges.CheckOutResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.GetOrdersByUserIdResponse;
import com.example.cartAndOrder.feignClient.ProductClient;
import com.example.cartAndOrder.repository.CartRepository;
import com.example.cartAndOrder.repository.OrderRepository;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServicesImpl implements OrderServices {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;
    @Override
    public CheckOutResponse checkOut(Order order) {

        Order order1 = orderRepository.save(order);
        CheckOutResponse response = new CheckOutResponse();
        response.setOrderId(order1.getOrderId());

        //clearing the cart
        Optional<Cart> cart = cartRepository.findById(order1.getUserId());
        if(cart.isPresent()){
            cartRepository.delete(cart.get());
        }

        // Updating the Product Stock
        ProductClient productClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ProductClient.class))
                .logLevel(Logger.Level.FULL)
                .target(ProductClient.class, "http://172.16.20.119:8081/product/update");

        List<CartProduct> cartProducts = order.getItems();
        Iterator<CartProduct> iterator = cartProducts.iterator();

        while (iterator.hasNext()){
            CartProduct cartProduct = iterator.next();

            productClient.updateStock(cartProduct.getProductId(),-cartProduct.getQuantity());
        }


        //TODO: Update the Merchant stock of product
        return response ;
    }

    @Override
    public GetOrdersByUserIdResponse findOrdersByUserId(String userId) {

        List<Order> orders = orderRepository.findByUserId(userId);

        GetOrdersByUserIdResponse getOrdersByUserIdResponse = new GetOrdersByUserIdResponse();
        getOrdersByUserIdResponse.setOrders(orders);
        return getOrdersByUserIdResponse;
    }
}
