package com.example.cartAndOrder.entity;


import com.example.cartAndOrder.exchanges.CartProduct;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
//@NoArgsConstructor
@lombok.Getter
@Setter
@Document(collection = "cart")
public class Cart {

    @Id
    private String customerId;
    private double totalAmount;
    private List<CartProduct> items;

    public Cart(){
        items = new ArrayList<>();
    }


}
