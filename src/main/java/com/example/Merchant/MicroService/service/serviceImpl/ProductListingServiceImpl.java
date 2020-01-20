package com.example.Merchant.MicroService.service.serviceImpl;

import com.example.Merchant.MicroService.DTO.MerchantDTO;
import com.example.Merchant.MicroService.Entity.MerchantEntity;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import com.example.Merchant.MicroService.repository.ProductListingRepository;
import com.example.Merchant.MicroService.service.ProductListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductListingServiceImpl implements ProductListingService
{

    @Autowired
    private ProductListingRepository productListingRepository;


    @Override
    public ProductListingEntity save(ProductListingEntity productListingEntity)
    {
        return productListingRepository.save(productListingEntity);
    }

    @Override
    public ResponseEntity<Integer> getProductListingRating(String productListingId)
    {
        ProductListingEntity productListingEntity=productListingRepository.findById(productListingId).get();
        return new ResponseEntity<Integer>(productListingEntity.getProductListingRating(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MerchantDTO>> getMerchantByProductId(String productId)
    {

    }

    @Override
    public void updateProductListingRating(int currentRating, String productListingId)
    {
        ProductListingEntity productListingEntity=productListingRepository.findById(productListingId).get();
        productListingRepository.deleteById(productListingId);
        int productListingRating=productListingEntity.getProductListingRating();
        int numberOfRatings=productListingEntity.getNumberOfRatings();
        int newRating=(productListingRating*numberOfRatings)/(numberOfRatings+1);
        productListingEntity.setProductListingRating(newRating);
        productListingRepository.save(productListingEntity);
    }
}
