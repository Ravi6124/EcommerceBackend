package com.example.Merchant.MicroService.service.serviceImpl;

import com.example.Merchant.MicroService.DTO.MerchantDTO;
import com.example.Merchant.MicroService.Entity.MerchantEntity;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import com.example.Merchant.MicroService.repository.MerchantRepository;
import com.example.Merchant.MicroService.service.MerchantService;
import com.example.Merchant.MicroService.service.ProductListingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService
{

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ProductListingService productListingService;

    @Override
    @Transactional
    public ResponseEntity<String> updateTotalProductSold(String merchantId, int quantity)
    {
        Optional<MerchantEntity> merchantEntity=Optional.empty();
        merchantEntity=merchantRepository.findById(merchantId);
        if(merchantEntity.isPresent())
        {
            int totalProductSold=merchantEntity.get().getTotalProductSold();
            totalProductSold+=quantity;
            merchantEntity.get().setTotalProductSold(totalProductSold);
            merchantRepository.deleteById(merchantId);
            merchantRepository.save(merchantEntity.get());


            //add logic to update total stock here too

            return new ResponseEntity<String>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateMerchantRating(double currentRating, String merchantId)
    {
        Optional<MerchantEntity> merchantEntity=merchantRepository.findById(merchantId);
        if(merchantEntity.isPresent())
        {
            double merchantRating = merchantEntity.get().getMerchantRating();
            int numberOfRatings = merchantEntity.get().getNumberOfMerchantRatings();
            double newRating = (merchantRating * numberOfRatings) + currentRating / (numberOfRatings + 1);
            merchantEntity.get().setMerchantRating(newRating);
            merchantRepository.deleteById(merchantEntity.get().getMerchantId());
            merchantRepository.save(merchantEntity.get());
            return  new ResponseEntity<String>(HttpStatus.OK);

            //add logic to update total stock here too

        }
        else
        {
            return  new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Double> getMerchantsRating(String merchantId)
    {
        Optional<MerchantEntity> merchantEntity=merchantRepository.findById(merchantId);
        if(merchantEntity.isPresent())
        {
            return new ResponseEntity<Double>(merchantEntity.get().getMerchantRating(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Double>(HttpStatus.BAD_REQUEST);
        }
    }



    @Override
    public ResponseEntity<MerchantDTO> findMerchantById (String merchantId)
    {
        Optional<MerchantEntity> merchantEntity=merchantRepository.findById(merchantId);
        if(merchantEntity.isPresent())
        {
            MerchantDTO merchantDTO=new MerchantDTO();
            BeanUtils.copyProperties(merchantEntity.get(),merchantDTO);
            return new ResponseEntity<MerchantDTO>(merchantDTO, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<MerchantDTO>(HttpStatus.BAD_REQUEST);
        }
    }



    @Override
    public MerchantEntity save(MerchantEntity merchantEntity)
    {
        return merchantRepository.save(merchantEntity);
    }



    @Override
    public ResponseEntity<Integer> getTotalProductSold(String merchantId)
    {
        Optional<MerchantEntity> merchantEntity=merchantRepository.findById(merchantId);
        if(merchantEntity.isPresent())
        {
            return new ResponseEntity<Integer>(merchantEntity.get().getTotalProductSold(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ProductListingEntity>> displayMerchantProducts(String merchantId)
    {
        return productListingService.displayMerchantsProducts(merchantId);
    }
}
