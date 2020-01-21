package com.example.ProductMicroServices.productmicroservicecontroller;

import com.example.ProductMicroServices.categoryservice.CategoryService;
import com.example.ProductMicroServices.dto.CategoryDto;
import com.example.ProductMicroServices.dto.ProductDto;
import com.example.ProductMicroServices.entity.CategoryEntity;
import com.example.ProductMicroServices.entity.ProductEntity;
import com.example.ProductMicroServices.productservice.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.dc.pr.PRError;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("product")
public class ProductMicroServiceController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @PostMapping("/category")
    public ResponseEntity<String> insert(@RequestBody CategoryDto categoryDto){
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(categoryDto,categoryEntity);
        CategoryEntity categoryCreated = categoryService.addCategory(categoryEntity);
        return new ResponseEntity<String>(categoryCreated.getCategoryId(),HttpStatus.CREATED);
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto,productEntity);
        ProductEntity productCreated = productService.addProduct(productEntity);
        return new ResponseEntity<String>(productCreated.getProductId(),HttpStatus.CREATED);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryEntity>> getAllCatgories(){
        return new ResponseEntity<List<CategoryEntity>>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @GetMapping("/category/{id}/{page}/{size}")
    public Page<ProductDto> getProductsByCategoryId(@PathVariable("id") String categoryId, @PathVariable("page") int page, @PathVariable("size") int size){
      Page<ProductEntity> productEntities =  productService.getProductsByCategoryId(categoryId,page,size);
      Page<ProductDto> productDtos = productEntities.map(new Function<ProductEntity, ProductDto>() {
          @Override
          public ProductDto apply(ProductEntity productEntity) {
              ProductDto productDto = new ProductDto();
              BeanUtils.copyProperties(productEntity,productDto);
              return productDto;
          }
      });
      return productDtos;
    }

    @GetMapping("{id}")
    ResponseEntity<ProductDto> getProductByProductId(@PathVariable("id") String productId){
        Optional<ProductDto> productDto= productService.getProductByProductId(productId);
        if(productDto.isPresent()){
            return new ResponseEntity<ProductDto>(productDto.get(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<ProductDto>(new ProductDto(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("present/{productName}")
    ResponseEntity<String> isProductPresent(@PathVariable("productName") String productName){
        return productService.isProductPresent(productName);
    }

    @PutMapping("update/{id}/{offset}")
    ResponseEntity<String> updateStock(@PathVariable("id") String productId,@PathVariable("offset") int offset){
        productService.updateStock(productId,offset);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
