package com.example.Merchant.MicroService.service;

import com.example.Merchant.MicroService.DTO.MerchantDTO;
import com.example.Merchant.MicroService.Entity.MerchantEntity;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductListingService
{

    ProductListingEntity save(ProductListingEntity productListingEntity);

    ResponseEntity<Integer> getProductListingRating(String productListingId);

    void updateProductListingRating(int currentRating, String productListingId);

    ResponseEntity<List<MerchantDTO>> getMerchantByProductId(String productId);
}
