package com.example.CustomerMicroService.customerservice;

import com.example.CustomerMicroService.entity.CustomerEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CustomerService {
   CustomerEntity addCustomer(CustomerEntity customerEntity);
   Optional<CustomerEntity> getCustomer(String customerId);
   CustomerEntity updateCustomer(CustomerEntity customerEntity);
}
