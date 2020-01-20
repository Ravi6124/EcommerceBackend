package com.example.CustomerMicroService.customercontroller;

import com.example.CustomerMicroService.customerservice.CustomerService;
import com.example.CustomerMicroService.dto.CustomerDto;
import com.example.CustomerMicroService.entity.CustomerEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customerDto){
        CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerDto,customerEntity);
        CustomerEntity customerCreated = customerService.addCustomer(customerEntity);
        return new ResponseEntity<String>(customerCreated.getCustomerId(),HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public Optional<CustomerEntity> getCustomer(@PathVariable("id") String customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping("update")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerDto customerDto){
        CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerDto,customerEntity);
        CustomerEntity customerCreated = customerService.updateCustomer(customerEntity);
        return new ResponseEntity<String>(customerCreated.getCustomerId(),HttpStatus.CREATED);
    }

}
