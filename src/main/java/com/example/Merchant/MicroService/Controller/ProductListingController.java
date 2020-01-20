package com.example.Merchant.MicroService.Controller;

import com.example.Merchant.MicroService.DTO.MerchantDTO;
import com.example.Merchant.MicroService.DTO.ProductListingDTO;
import com.example.Merchant.MicroService.Entity.MerchantEntity;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import com.example.Merchant.MicroService.service.ProductListingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ProductListingController")
public class ProductListingController
{
    @Autowired
    private ProductListingService productListingService;

    @PostMapping(value="/addProductListing")
    public ResponseEntity<String> addProductListing(@RequestBody ProductListingDTO productListingDTO)
    {
        ProductListingEntity productListingEntity=new ProductListingEntity();
        BeanUtils.copyProperties(productListingDTO,productListingEntity);
        ProductListingEntity productListCreated=productListingService.save(productListingEntity);
        return new ResponseEntity<String>(productListCreated.getProductListingId(),HttpStatus.CREATED);
    }

    @GetMapping(value="/getProductListingRating")
    public ResponseEntity<Integer> getProductListingRating(String productListingId)
    {
        return productListingService.getProductListingRating(productListingId);

    }

    @PutMapping(value="/updateProductListingRating")
    public ResponseEntity<String> updateProductListingRating(@RequestBody int currentRating,String productListingId)
    {
        productListingService.updateProductListingRating(currentRating,productListingId);
        return new ResponseEntity<String >(HttpStatus.OK);
    }

    @GetMapping(value="/getMerchantByProductId")
    public ResponseEntity<List<MerchantDTO>> getMerchantByProductId(String productId)
    {
        return productListingService.getMerchantByProductId(productId);
    }

}



