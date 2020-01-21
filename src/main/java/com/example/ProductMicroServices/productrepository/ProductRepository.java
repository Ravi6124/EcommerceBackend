package com.example.ProductMicroServices.productrepository;

import com.example.ProductMicroServices.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity,String> {
    Page<ProductEntity> findByCategoryId(String categoryId, Pageable pageable);

}
