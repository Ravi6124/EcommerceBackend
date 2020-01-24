package com.example.ProductMicroServices.categoryservice.categoryserviceimpl;

import com.example.ProductMicroServices.categoryrepository.CategoryRepository;
import com.example.ProductMicroServices.categoryservice.CategoryService;
import com.example.ProductMicroServices.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryEntity addCategory(CategoryEntity categoryEntity) {
      return categoryRepository.insert(categoryEntity);
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
       return categoryRepository.findAll();
    }
}
