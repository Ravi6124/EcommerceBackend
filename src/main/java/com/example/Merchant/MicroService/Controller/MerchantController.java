package com.example.Merchant.MicroService.Controller;

import com.example.Merchant.MicroService.DTO.MerchantDTO;
import com.example.Merchant.MicroService.Entity.MerchantEntity;
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
    public ResponseEntity<Optional<MerchantDTO>> getMerchantById (@RequestBody String merchantId)
    {
       Optional<MerchantEntity> merchantEntity =  merchantService.findById(merchantId);
       MerchantDTO merchantDTO=new MerchantDTO();
       BeanUtils.copyProperties(merchantEntity.get(),merchantDTO);
       Optional<MerchantDTO> returnMerchant = Optional.of(merchantDTO);
       return new ResponseEntity<Optional<MerchantDTO>>(returnMerchant,HttpStatus.CREATED);
    }

    @GetMapping(value="/getMerchantRating")
    public ResponseEntity<Integer> getMerchantRating(String merchantId)
    {
        return merchantService.getMerchantRating(merchantId);

    }

    @PutMapping(value="/updateMerchantRating")
    public ResponseEntity<String > updateMerchantRating(@RequestBody int currentRating,String merchantId)
    {
        merchantService.updateMerchantRating(currentRating,merchantId);
        return new ResponseEntity<String >(HttpStatus.OK);
    }

    @PutMapping(value="/updateTotalProductSold")
    public ResponseEntity<String> updateTotalProductSold(String merchantId,int quantity)
    {
        merchantService.updateMerchantRating(curre;
        return new ResponseEntity<String >(HttpStatus.OK);
    }





GetTotalProductSold(merchantId)                      //Merchant
    Type - Get
    Response - int / totalProductSold
    RequestParam - merchantId



}
