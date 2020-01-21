package com.example.Merchant.MicroService.service;
import com.example.Merchant.MicroService.DTO.MerchantDTO;
import com.example.Merchant.MicroService.Entity.MerchantEntity;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface MerchantService
{
    MerchantEntity save(MerchantEntity merchantEntity);

    ResponseEntity<MerchantDTO> findMerchantById(String merchantId);

    ResponseEntity<Double> getMerchantsRating(String merchantId);

    ResponseEntity<String> updateMerchantRating(double currentRating, String merchantId);

    ResponseEntity<String> updateTotalProductSold(String merchantId,int quantity);

    ResponseEntity<Integer> getTotalProductSold(String merchantId);

}
