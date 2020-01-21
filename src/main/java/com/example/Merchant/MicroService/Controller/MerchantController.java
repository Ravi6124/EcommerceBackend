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
import java.util.Optional;

@RequestMapping("/merchant")
@RestController
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

    @GetMapping(value="/getMerchantById")
    public ResponseEntity<MerchantDTO> getMerchantById (@RequestBody String merchantId)
    {
        return merchantService.findMerchantById(merchantId);
    }

    @GetMapping(value="/getMerchantRating")
    public ResponseEntity<Double> getMerchantRating(String merchantId)
    {
        return merchantService.getMerchantsRating(merchantId);

    }

    @PutMapping(value="/updateMerchantRating")
    public ResponseEntity<String > updateMerchantRating(@RequestBody double currentRating,String merchantId)
    {
        return merchantService.updateMerchantRating(currentRating, merchantId);
    }

    @PutMapping(value="/updateTotalProductSold")
    public ResponseEntity<String> updateTotalProductSold(String merchantId,int quantity)
    {

        return merchantService.updateTotalProductSold(merchantId,quantity);
    }

    @GetMapping(value="/getTotalProductSold")
    public ResponseEntity<Integer> getTotalProductSold(String merchantId)
    {
        return merchantService.getTotalProductSold(merchantId);
    }




}
