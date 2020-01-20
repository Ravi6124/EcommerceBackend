package com.example.ProductMicroServices.productservice;

import com.example.ProductMicroServices.dto.ProductDto;
import com.example.ProductMicroServices.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ProductService {
    ProductEntity addProduct(ProductEntity productEntity);
    Page<ProductEntity> getProductsByCategoryId(String categoryId, int page, int size);
    Optional<ProductDto> getProductByProductId(String productId);
    ResponseEntity<String> isProductPresent(String productName);
    void updateStock(String productId,int stockOffset);

}
