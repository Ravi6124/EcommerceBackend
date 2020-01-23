package com.example.cartAndOrder.services;

import com.example.cartAndOrder.entity.Order;
import com.example.cartAndOrder.exchanges.orderExchanges.CheckOutResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.FindOrdersByMidResponse;
import com.example.cartAndOrder.exchanges.orderExchanges.GetOrdersByUserIdResponse;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.List;

public interface OrderServices {

    CheckOutResponse checkOut(Order order);
    GetOrdersByUserIdResponse findOrdersByUserId(String userId);
    void mail(String email) throws MessagingException;
    List<FindOrdersByMidResponse> findOrdersByMid(String mid);
}
