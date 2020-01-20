package com.example.Merchant.MicroService.service.serviceImpl;

import com.example.Merchant.MicroService.Entity.MerchantEntity;
import com.example.Merchant.MicroService.repository.MerchantRepository;
import com.example.Merchant.MicroService.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService
{

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public void updateMerchantRating(int currentRating, String merchantId)
    {
        MerchantEntity merchantEntity=merchantRepository.findById(merchantId).get();
        merchantRepository.deleteById(merchantId);
        int merchantRating=merchantEntity.getMerchantRating();
        int numberOfRatings=merchantEntity.getNumberOfMerchantRatings();
        int newRating=(merchantRating*numberOfRatings)/(numberOfRatings+1);
        merchantEntity.setMerchantRating(newRating);
        merchantRepository.save(merchantEntity);
    }

    @Override
    public ResponseEntity<Integer> getMerchantRating(String merchantId)
    {
        MerchantEntity merchantEntity=merchantRepository.findById(merchantId).get();
        return new ResponseEntity<Integer>(merchantEntity.getMerchantRating(), HttpStatus.OK);
    }

    @Override
    public Optional<MerchantEntity> findById(String merchantId)

    {
        return merchantRepository.findById(merchantId);
    }

    @Override
    public MerchantEntity save(MerchantEntity merchantEntity)

    {
        return merchantRepository.save(merchantEntity);
    }

}
