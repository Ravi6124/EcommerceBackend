package com.example.Merchant.MicroService.Controller;

import com.example.Merchant.MicroService.DTO.*;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import com.example.Merchant.MicroService.service.ProductListingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("productListingController")

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping(value="/getProductListingRating/{productId}/{merchantId}")
    public ResponseEntity<Double> getProductListingRating(@PathVariable("productId") String productId,@PathVariable("merchantId")String merchantId)
    {
        return productListingService.getProductListingRating(productId,merchantId);

    }

    @PutMapping(value="/updateProductListingRating/{productId}/{merchantId}/{currentRating}")
    public ResponseEntity<String> updateProductListingRating(@PathVariable("productId") String productId,@PathVariable("merchantId")String merchantId,@PathVariable("currentRating") double currentRating)
    {
        return productListingService.updateProductListingRating(currentRating,productId,merchantId);
    }

    @GetMapping(value="/getMerchantByProductId/{productId}")
    public ResponseEntity<List<GetMerchantsbyPidResponse>> getMerchantByProductId(@PathVariable("productId") String productId)
    {
        return  new ResponseEntity<List<GetMerchantsbyPidResponse>>(productListingService.findMerchantsbyPid(productId),HttpStatus.OK);
    }

    @PostMapping(value = "/checkstock")
    public CheckStockResponse checkStock(@RequestBody CheckStockAndUpdateRequest checkStockAndUpdateRequest){

        List<CartProduct> cartProducts = checkStockAndUpdateRequest.getCartProducts();

        return productListingService.checkProductStock(cartProducts);

    }

    @PutMapping(value = "/updatestock")
    public ResponseEntity<Boolean> updateStock(@RequestBody CheckStockAndUpdateRequest checkStockAndUpdateRequest){
        List<CartProduct> cartProducts = checkStockAndUpdateRequest.getCartProducts();

        if(cartProducts.size() == 0){
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }

        productListingService.updateStock(cartProducts);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @PostMapping(value="/increaseProductStock/{productName}/{merchantId}/{offset}")
    public ResponseEntity<String> increaseProductStock(@PathVariable("productName") String productName,@PathVariable("merchantId") String merchantId,@PathVariable("offset") int offset)
    {
        return productListingService.increaseProductStock(productName,merchantId,offset);
    }

    @GetMapping(value="/getStock/{productListingId}")
    public ResponseEntity<Integer> getStock(@PathVariable("productListingId") String productListingId)
    {
        return productListingService.getStock(productListingId);
    }

    @GetMapping(value="/getSearchProductExtraDetails/{merchantId}/{productId}")
    public GetSearchProductExtraDetailsResponse getSearchProductExtraDetails(@PathVariable("merchantId") String merchantId, @PathVariable("productId") String productId)
    {
        return productListingService.getSearchProductExtraDetails(merchantId,productId);
    }



}



