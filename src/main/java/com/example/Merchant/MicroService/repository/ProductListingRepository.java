package com.example.Merchant.MicroService.repository;

import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductListingRepository extends MongoRepository<ProductListingEntity,String>
{

}
