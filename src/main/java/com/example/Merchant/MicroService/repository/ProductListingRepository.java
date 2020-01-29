package com.example.Merchant.MicroService.repository;

import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductListingRepository extends MongoRepository<ProductListingEntity,String>
{
    List<ProductListingEntity> findByProductId(String productId);

    Optional<ProductListingEntity> findByProductIdAndMerchantId(String productId, String merchantId);

    List<ProductListingEntity> findByMerchantId(String merchantId);

    ProductListingEntity findByProductNameAndMerchantId(String productName,String merchantId);

}