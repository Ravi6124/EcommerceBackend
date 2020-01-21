package com.example.cartAndOrder.entity;

import com.example.cartAndOrder.exchanges.CartProduct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {

    private String oderId;
    private String userId;
    private Date date;
    private List<CartProduct> items;
    private double totalAmount;

}
