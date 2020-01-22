package com.example.ProductMicroServices.productservice.productserviceimpl;

import com.example.ProductMicroServices.dto.ProductDto;
import com.example.ProductMicroServices.dto.ProductMerchant;
import com.example.ProductMicroServices.entity.ProductEntity;
import com.example.ProductMicroServices.productrepository.ProductRepository;
import com.example.ProductMicroServices.productservice.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity addProduct(ProductEntity productEntity) {
        return productRepository.insert(productEntity);
    }

    @Override
    public Page<ProductEntity> getProductsByCategoryId(String categoryId, int pageNumber, int pageSize) {
      //  List<ProductDto> returnList = new ArrayList<>();
        return productRepository.findByCategoryId(categoryId, PageRequest.of(pageNumber, pageSize));

        //        List<ProductEntity> allProducts = productRepository.findAll();
//        Iterator<ProductEntity> iterator=  allProducts.iterator();
//        while(iterator.hasNext()){
//            ProductEntity productEntity = iterator.next();
//            if(productEntity.getCategoryId().equals(categoryId)){
//                ProductDto productDto = new ProductDto();
//                BeanUtils.copyProperties(productEntity,productDto);
//                returnList.add(productDto);
//            }
//        }
//        int start = (int) PageRequest.of(page, size).getOffset();
//        int end = (start + PageRequest.of(page, size).getPageSize()) > returnList.size() ? returnList.size() : (start + PageRequest.of(page, size).getPageSize());
//        return new PageImpl<ProductDto>(returnList.subList(start, end), PageRequest.of(page, size), returnList.size());
    }

    @Override
    public Optional<ProductDto> getProductByProductId(String productId) {
        Optional<ProductEntity> productEntity= productRepository.findById(productId);
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productEntity.get(),productDto);
        return Optional.of(productDto);
    }

    @Override
    public ResponseEntity<String> isProductPresent(String productName) {
        String returnValue = "#";
        List<ProductEntity> allProducts = productRepository.findAll();
        Iterator<ProductEntity> iterator=  allProducts.iterator();
        while(iterator.hasNext()){
            ProductEntity productEntity = iterator.next();
            if(productName.equals(productEntity.getProductName()))
            returnValue = productEntity.getProductId();
        }
        return  new ResponseEntity<String>(returnValue,HttpStatus.CREATED);
    }

    @Override
    public void updateStock(String productId, int stockOffset)
    {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if(productEntity.isPresent()) {
            ProductEntity productEntityCopy = productEntity.get();
            productRepository.delete(productEntity.get());
            productEntity.get().setTotalStock(productEntityCopy.getTotalStock() + stockOffset);
            productRepository.insert(productEntityCopy);
        }
    }

    @Override
    public ResponseEntity<String> updateMerchantPrice(String productId,String merchnatId,double price) {
        Optional<ProductDto> productDto = getProductByProductId(productId);
        if(productDto.isPresent()){
            ProductDto product = productDto.get();
            ProductEntity productEntity = new ProductEntity();
            BeanUtils.copyProperties(product,productEntity);
            productEntity.setDefaultMerchantId(merchnatId);
            productEntity.setDefaultPrice(price);
            productRepository.delete(productEntity);
            productRepository.insert(productEntity);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

}
