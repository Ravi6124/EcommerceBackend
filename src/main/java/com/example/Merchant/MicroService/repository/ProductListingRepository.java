package com.example.Merchant.MicroService.repository;

import com.example.Merchant.MicroService.Entity.MerchantEntity;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductListingRepository extends MongoRepository<ProductListingEntity,String>
{
    List<ProductListingEntity> findByProductId(String productId);
}
