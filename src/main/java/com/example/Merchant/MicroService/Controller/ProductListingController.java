package com.example.Merchant.MicroService.Controller;

import com.example.Merchant.MicroService.DTO.CheckStockAndUpdateRequest;
import com.example.Merchant.MicroService.DTO.CheckStockAndUpdateResponse;
import com.example.Merchant.MicroService.DTO.GetMerchantsbyPidResponse;
import com.example.Merchant.MicroService.DTO.ProductListingDTO;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import com.example.Merchant.MicroService.service.ProductListingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("productListingController")
public class ProductListingController
{
    @Autowired
    private ProductListingService productListingService;

    @PostMapping(value="/addProductListing")
    public ResponseEntity<String> addProductListing(@RequestBody ProductListingDTO productListingDTO)
    {
        //System.out.println("before");
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
        return  new ResponseEntity<List<GetMerchantsbyPidResponse>>(productListingService.findMerchantsbyPid(productId),HttpStatus.OK);
    }

    @PutMapping(value="/checkStockAndUpdate/{productListId}/{requiredQuantity}")
    public ResponseEntity<CheckStockAndUpdateResponse> checkProductStockAndUpdate(@Valid @RequestBody CheckStockAndUpdateRequest checkStockAndUpdateRequest)
    {
        String productId = checkStockAndUpdateRequest.getProductId();
        String merChantId = checkStockAndUpdateRequest.getMerchantId();
        int quantity = checkStockAndUpdateRequest.getQuantity();

        CheckStockAndUpdateResponse response = productListingService.checkProductStockAndUpdate(productId,merChantId,quantity);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping(value="/increaseProductStock/{productListId}/{offset}")
    public ResponseEntity<String> increaseProductStock(@PathVariable("productListId") String productListId,@PathVariable("offset") int offset)
    {
        return productListingService.increaseProductStock(productListId,offset);
    }

    @GetMapping(value="/getStock/{productListingId}")
    public ResponseEntity<Integer> getStock(@PathVariable("productListingId") String productListingId)
    {
        return productListingService.getStock(productListingId);
    }




}



