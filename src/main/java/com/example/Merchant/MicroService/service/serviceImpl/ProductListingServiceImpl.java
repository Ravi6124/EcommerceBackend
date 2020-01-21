package com.example.Merchant.MicroService.service.serviceImpl;

import com.example.Merchant.MicroService.DTO.GetMerchantsbyPidResponse;
import com.example.Merchant.MicroService.Entity.MerchantEntity;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import com.example.Merchant.MicroService.repository.MerchantRepository;
import com.example.Merchant.MicroService.repository.ProductListingRepository;
import com.example.Merchant.MicroService.service.ProductListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductListingServiceImpl implements ProductListingService
{

    @Autowired
    ProductListingRepository productListingRepository;

    @Autowired
    MerchantRepository merchantRepository;


    @Override
    public ProductListingEntity save(ProductListingEntity productListingEntity)
    {
        return productListingRepository.save(productListingEntity);
    }

    @Override
    public ResponseEntity<Double> getProductListingRating(String productListingId)
    {
        Optional<ProductListingEntity> productListingEntity=productListingRepository.findById(productListingId);
        if(productListingEntity.isPresent())
        {
            return new ResponseEntity<Double>(productListingEntity.get().getProductListingRating(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Double>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<String> checkProductStockAndUpdate(String productListingId,int requiredQuantity)
    {
        Optional<ProductListingEntity> productListingEntity=productListingRepository.findById(productListingId);

        if(productListingEntity.isPresent())
        {
            if(productListingEntity.get().getQuantity()>=requiredQuantity)
            {
                productListingEntity.get().setQuantity(productListingEntity.get().getQuantity()-requiredQuantity);
                return new ResponseEntity<String>(HttpStatus.OK);
            }
            else
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Integer> getStock(String productListingId)
    {
        Optional<ProductListingEntity> productListingEntity=productListingRepository.findById(productListingId);

        if(productListingEntity.isPresent())
        {
            return new ResponseEntity<Integer>(productListingEntity.get().getQuantity(),HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<String> increaseProductStock(String productListingId, int offset)
    {
        Optional<ProductListingEntity> productListingEntity=productListingRepository.findById(productListingId);

        if(productListingEntity.isPresent())
        {
            productListingEntity.get().setQuantity(productListingEntity.get().getQuantity()+offset);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<GetMerchantsbyPidResponse>> findMerchantsbyPid(String productId)
    {
        List<ProductListingEntity> productListingEntityList = productListingRepository.findByProductId(productId);
        List<GetMerchantsbyPidResponse> result = new ArrayList<>();
        Iterator<ProductListingEntity> iterator = productListingEntityList.iterator();

        //finding all the Name of the merchants

        while(iterator.hasNext())
        {

            GetMerchantsbyPidResponse getMerchantsbyPidResponse = new GetMerchantsbyPidResponse();

            //Adding all the required information to the response DTO
            getMerchantsbyPidResponse.setMerchantId(iterator.next().getMerchantId());
            getMerchantsbyPidResponse.setCost(iterator.next().getPrice());
            getMerchantsbyPidResponse.setProductRating(iterator.next().getProductListingRating());
            getMerchantsbyPidResponse.setProductId(productId);

            Optional<MerchantEntity> merchantEntity = merchantRepository.findById(iterator.next().getMerchantId());
            if(merchantEntity.isPresent())
            {
                getMerchantsbyPidResponse.setMerchantName(merchantEntity.get().getFirstName());
            }

            result.add(getMerchantsbyPidResponse);

        }

        return new ResponseEntity<List<GetMerchantsbyPidResponse>>(result,HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<String> updateProductListingRating(double currentRating, String productListingId)
    {
        Optional<ProductListingEntity> productListingEntity=productListingRepository.findById(productListingId);
        if( productListingEntity.isPresent())
        {
            double productListingRating=productListingEntity.get().getProductListingRating();
            int numberOfRatings=productListingEntity.get().getNumberOfRatings();
            double newRating=(productListingRating*numberOfRatings)/(numberOfRatings+1);
            productListingEntity.get().setProductListingRating(newRating);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }
}
