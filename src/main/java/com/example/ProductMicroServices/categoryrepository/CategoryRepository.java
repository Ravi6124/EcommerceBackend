package com.example.ProductMicroServices.categoryrepository;

import com.example.ProductMicroServices.entity.CategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepository extends MongoRepository<CategoryEntity,String> {
}
