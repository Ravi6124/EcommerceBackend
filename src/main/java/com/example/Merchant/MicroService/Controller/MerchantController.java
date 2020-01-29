package com.example.Merchant.MicroService.Controller;

import com.example.Merchant.MicroService.DTO.MerchantDTO;
import com.example.Merchant.MicroService.Entity.MerchantEntity;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import com.example.Merchant.MicroService.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RequestMapping("/merchant")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MerchantController
{

   @Autowired
    private MerchantService merchantService;

    @PostMapping(value="/addMerchant")
    public ResponseEntity<String> addMerchant(@RequestBody MerchantDTO merchantDTO)
    {
        MerchantEntity merchantEntity=new MerchantEntity();
        BeanUtils.copyProperties(merchantDTO,merchantEntity);
        MerchantEntity merchantCreated=merchantService.save(merchantEntity);
        return new ResponseEntity<String>(merchantCreated.getMerchantId(),HttpStatus.CREATED);
    }

    @GetMapping(value="/getMerchantById/{merchantId}")
    public ResponseEntity<MerchantDTO> getMerchantById (@PathVariable("merchantId") String merchantId)
    {
        return merchantService.findMerchantById(merchantId);
    }

    @GetMapping(value="/getMerchantRating/{merchantId}")
    public ResponseEntity<Double> getMerchantRating(@PathVariable("merchantId") String merchantId)
    {
        return merchantService.getMerchantsRating(merchantId);

    }

    @PutMapping(value="/updateMerchantRating/{currentRating}/{merchantId}")
    public ResponseEntity<String > updateMerchantRating(@PathVariable("currentRating") double currentRating,@PathVariable("merchantId") String merchantId)
    {
        return merchantService.updateMerchantRating(currentRating, merchantId);
    }

    @PutMapping(value="/updateTotalProductSold/{merchantId}/{quantity}")
    public ResponseEntity<String> updateTotalProductSold(@PathVariable("merchantId") String merchantId,@PathVariable("quantity") int quantity)
    {

        return merchantService.updateTotalProductSold(merchantId,quantity);
    }

    @GetMapping(value="/getTotalProductSold/{merchantId}")
    public ResponseEntity<Integer> getTotalProductSold(@PathVariable("merchantId") String merchantId)
    {
        return merchantService.getTotalProductSold(merchantId);
    }

    @GetMapping(value="/displayMerchantProducts/{merchantId}")
    public ResponseEntity<List<ProductListingEntity>> displayMerchantProducts(@PathVariable("merchantId")String merchantId)
    {
        return merchantService.displayMerchantProducts(merchantId);
    }

    @PostMapping(value="/updateMerchant")
    public ResponseEntity<HttpStatus> updateMerchant(@RequestBody MerchantDTO merchantDTO)
    {
        return merchantService.updateMerchant(merchantDTO);
    }


}
