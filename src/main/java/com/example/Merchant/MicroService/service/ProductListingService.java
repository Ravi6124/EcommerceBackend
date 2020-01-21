package com.example.Merchant.MicroService.service;

import com.example.Merchant.MicroService.DTO.GetMerchantsbyPidResponse;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductListingService
{

    ProductListingEntity save(ProductListingEntity productListingEntity);

    ResponseEntity<Double> getProductListingRating(String productListingId);

    ResponseEntity<String> updateProductListingRating(double currentRating, String productListingId);

    ResponseEntity<List<GetMerchantsbyPidResponse>> findMerchantsbyPid(String productId);

    ResponseEntity<String> checkProductStockAndUpdate(String productListingId,int requiredQuantity);

    ResponseEntity<String> increaseProductStock(String productListId, int offset);

    ResponseEntity<Integer> getStock(String productListingId);

}
