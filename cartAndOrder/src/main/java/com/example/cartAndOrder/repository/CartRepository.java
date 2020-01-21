package com.example.cartAndOrder.repository;

import com.example.cartAndOrder.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart,String> {
}
