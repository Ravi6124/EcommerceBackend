package com.example.cartAndOrder.exchanges.orderExchanges;

import com.example.cartAndOrder.entity.Order;
import com.example.cartAndOrder.exchanges.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersByUserIdResponse {

    List<Order> orders;

}
