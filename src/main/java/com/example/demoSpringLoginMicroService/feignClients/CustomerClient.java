package com.example.demoSpringLoginMicroService.feignClients;

import com.example.demoSpringLoginMicroService.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CustomerClient", url="172.16.20.119:8090/customer")
public interface CustomerClient {

    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customerDto);

}
