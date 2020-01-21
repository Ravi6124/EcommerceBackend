package com.example.Merchant.MicroService.Controller;

import com.example.Merchant.MicroService.DTO.GetMerchantsbyPidResponse;
import com.example.Merchant.MicroService.DTO.ProductListingDTO;
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

    @GetMapping(value="/getProductListingRating/{productListingId}")
    public ResponseEntity<Double> getProductListingRating(@PathVariable("productListingId") String productListingId)
    {
        return productListingService.getProductListingRating(productListingId);

    }

    @PutMapping(value="/updateProductListingRating/{productListingId}/{currentRating}")
    public ResponseEntity<String> updateProductListingRating(@PathVariable("productListingId") String productListingId,@PathVariable("currentRating") double currentRating)
    {
        return productListingService.updateProductListingRating(currentRating,productListingId);
    }

    @GetMapping(value="/getMerchantByProductId/{productId}")
    public ResponseEntity<List<GetMerchantsbyPidResponse>> getMerchantByProductId(@PathVariable("productId") String productId)
    {
        return  productListingService.findMerchantsbyPid(productId);
    }

    @GetMapping(value="/checkStockAndUpdate/{productListId}/{requiredQuantity}")
    public ResponseEntity<String> checkProductStockAndUpdate(String productListId, int requiredQuantity)
    {
        return productListingService.checkProductStockAndUpdate(productListId,requiredQuantity);
    }

    @PutMapping(value="/increaseProductStock")
    public ResponseEntity<String> increaseProductStock(String productListId, int offset)
    {
        return productListingService.increaseProductStock(productListId,offset);
    }

    @GetMapping(value="/getStock")
    public ResponseEntity<Integer> getStock(String productListingId)
    {
        return productListingService.getStock(productListingId);
    }




}



