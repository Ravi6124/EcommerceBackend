package com.example.cartAndOrder.feignClient;

import feign.Param;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;

public interface CustomerClient {

    @RequestLine("GET /{id}")
    String getEmail(@Param("id") String customerId);
}
