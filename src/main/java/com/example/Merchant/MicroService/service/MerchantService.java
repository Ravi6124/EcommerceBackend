package com.example.Merchant.MicroService.service;
import com.example.Merchant.MicroService.Entity.MerchantEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface MerchantService
{
    public MerchantEntity save(MerchantEntity merchantEntity);

    Optional<MerchantEntity> findById(String merchantId);

    ResponseEntity<Integer> getMerchantRating(String merchantId);

    void updateMerchantRating(int currentRating, String merchantId);
}
