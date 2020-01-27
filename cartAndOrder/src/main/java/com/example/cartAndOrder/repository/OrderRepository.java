package com.example.cartAndOrder.repository;

import com.example.cartAndOrder.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String> {

    List<Order> findByUserId(String userId);

}
