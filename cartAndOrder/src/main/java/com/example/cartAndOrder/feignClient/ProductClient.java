package com.example.cartAndOrder.feignClient;

import feign.Param;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;

public interface ProductClient {

    @RequestLine("PUT /{productId}/{offset}")
    ResponseEntity<String> updateStock(@Param("productId") String productId,@Param("offset") int offset);


}
