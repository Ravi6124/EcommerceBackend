package com.example.Merchant.MicroService.service;

import com.example.Merchant.MicroService.DTO.CartProduct;
import com.example.Merchant.MicroService.DTO.CheckStockAndUpdateRequest;
import com.example.Merchant.MicroService.DTO.CheckStockResponse;
import com.example.Merchant.MicroService.DTO.GetMerchantsbyPidResponse;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductListingService
{

    ProductListingEntity save(ProductListingEntity productListingEntity);

    ResponseEntity<Double> getProductListingRating(String productListingId);

    ResponseEntity<String> updateProductListingRating(double currentRating, String productListingId);

    List<GetMerchantsbyPidResponse> findMerchantsbyPid(String productId);

    CheckStockResponse checkProductStock(List<CartProduct> cartProducts);

    ResponseEntity<String> increaseProductStock(String productListId, int offset);

    ResponseEntity<Integer> getStock(String productListingId);

    void updateStock( List<CartProduct> cartProducts);

    void setDefaultMerchantIdAndDefaultPrice(String productId);

   ResponseEntity<List<ProductListingEntity>> displayMerchantsProducts(String merchantId);
}
