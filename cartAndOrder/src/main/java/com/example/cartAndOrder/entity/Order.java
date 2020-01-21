package com.example.cartAndOrder.entity;

import com.example.cartAndOrder.exchanges.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "orders")
public class Order {

    @Id
    private String orderId;
    private String userId;
    private Date date;
    private List<CartProduct> items;
    private double totalAmount;

}
