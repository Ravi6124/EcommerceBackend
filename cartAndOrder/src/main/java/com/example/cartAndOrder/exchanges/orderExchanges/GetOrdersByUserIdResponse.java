package com.example.cartAndOrder.exchanges.orderExchanges;

import com.example.cartAndOrder.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersByUserIdResponse {

    List<Order> orders;

}
