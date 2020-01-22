package com.example.Merchant.MicroService.productClient;

import com.example.Merchant.MicroService.DTO.ProductMerchant;
import feign.Param;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductSaveClient
{
        @RequestLine("PUT /{productId}/{merchantId}/{price}")
        ResponseEntity<String> updateMerchantPrice(@Param("productId") String productId,@Param("merchantId") String merchantId,@Param("price") double price);

}
