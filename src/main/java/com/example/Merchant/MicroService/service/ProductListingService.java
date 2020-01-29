package com.example.Merchant.MicroService.service;

import com.example.Merchant.MicroService.DTO.*;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductListingService
{

    ProductListingEntity save(ProductListingEntity productListingEntity);

    ResponseEntity<Double> getProductListingRating(String productId,String merchantId);

    ResponseEntity<String> updateProductListingRating(double currentRating, String productId,String merchantId);

    List<GetMerchantsbyPidResponse> findMerchantsbyPid(String productId);

    CheckStockResponse checkProductStock(List<CartProduct> cartProducts);

    ResponseEntity<String> increaseProductStock(String productName,String merchantId, int offset);

    ResponseEntity<Integer> getStock(String productListingId);

    void updateStock( List<CartProduct> cartProducts);

    void setDefaultMerchantIdAndDefaultPrice(String productId);

   ResponseEntity<List<ProductListingEntity>> displayMerchantsProducts(String merchantId);

    GetSearchProductExtraDetailsResponse getSearchProductExtraDetails(String merchantId, String productId);
}
