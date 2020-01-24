package com.example.ProductMicroServices.categoryservice;

import com.example.ProductMicroServices.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    CategoryEntity addCategory(CategoryEntity categoryEntity);
    List<CategoryEntity> getAllCategories();
}
