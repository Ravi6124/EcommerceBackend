package com.example.Merchant.MicroService.productClient;

import feign.Param;
import feign.RequestLine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public interface ProductStockUpdateClient
{
    @RequestLine("PUT /{id}/{offset}")
    ResponseEntity<String> updateStock(@Param("id") String productId, @Param("offset") int offset);
}