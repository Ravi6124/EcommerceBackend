package com.example.Merchant.MicroService.repository;

import com.example.Merchant.MicroService.Entity.MerchantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MerchantRepository extends MongoRepository<MerchantEntity,String>
{

}
