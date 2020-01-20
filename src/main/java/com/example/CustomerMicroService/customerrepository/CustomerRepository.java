package com.example.CustomerMicroService.customerrepository;

import com.example.CustomerMicroService.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity,String> {
}
