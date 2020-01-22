package com.example.Merchant.MicroService.productClient;

import com.example.Merchant.MicroService.DTO.ProductDTO;
import com.example.Merchant.MicroService.DTO.ProductMerchant;
import feign.Param;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


public interface ProductClient
{
    @RequestLine("GET /{productId}")
    ProductDTO getProductByProductId(@Param("productId") String productId);

}
