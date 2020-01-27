package com.example.cartAndOrder.feignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "LoginClient", url = "http://172.16.20.121:8092")

public interface LoginClient {

    @GetMapping("/customer/email/{id}")
    public String getEmailById(@PathVariable(value = "id") String Id);
}
