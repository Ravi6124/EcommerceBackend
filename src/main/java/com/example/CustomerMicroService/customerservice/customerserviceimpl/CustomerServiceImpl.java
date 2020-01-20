package com.example.CustomerMicroService.customerservice.customerserviceimpl;

import com.example.CustomerMicroService.customerrepository.CustomerRepository;
import com.example.CustomerMicroService.customerservice.CustomerService;
import com.example.CustomerMicroService.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerEntity addCustomer(CustomerEntity customerEntity) {
       return customerRepository.save(customerEntity);
    }

    @Override
    public Optional<CustomerEntity> getCustomer(String customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public CustomerEntity updateCustomer(CustomerEntity customerEntity) {
        Optional<CustomerEntity> customer = customerRepository.findById(customerEntity.getCustomerId());
        customerRepository.deleteById(customerEntity.getCustomerId());
        return addCustomer(customerEntity);
    }
}
