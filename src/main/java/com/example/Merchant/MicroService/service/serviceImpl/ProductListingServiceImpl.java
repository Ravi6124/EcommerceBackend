package com.example.Merchant.MicroService.service.serviceImpl;

import com.example.Merchant.MicroService.DTO.*;
import com.example.Merchant.MicroService.Entity.MerchantEntity;
import com.example.Merchant.MicroService.Entity.ProductListingEntity;
import com.example.Merchant.MicroService.productClient.ProductClient;
import com.example.Merchant.MicroService.productClient.ProductSaveClient;
import com.example.Merchant.MicroService.productClient.ProductStockUpdateClient;
import com.example.Merchant.MicroService.repository.MerchantRepository;
import com.example.Merchant.MicroService.repository.ProductListingRepository;
import com.example.Merchant.MicroService.service.ProductListingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductListingServiceImpl implements ProductListingService
{

    @Autowired
    ProductListingRepository productListingRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    @Override
    public ProductListingEntity save(ProductListingEntity productListingEntity)
    {




        ProductStockUpdateClient productStockUpdateClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ProductStockUpdateClient.class))
                .logLevel(Logger.Level.FULL)
                .target(ProductStockUpdateClient.class, "http://172.16.20.119:8081/product/update");

        productStockUpdateClient.updateStock(productListingEntity.getProductId(),productListingEntity.getQuantity());

        //ProductListingDTO productListingDTO=new ProductListingDTO();

        //kafka part starts
        ObjectMapper objectMapper=new ObjectMapper();
        String object_to_string= new String();
        try{
            object_to_string=objectMapper.writeValueAsString(productListingEntity);
            System.out.println(object_to_string);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        this.kafkaTemplate.send("productUpdate",object_to_string);
        //System.out.println(productListingEntity.toString() +" sent to kafka");
        //kafka part ends


        return productListingRepository.save(productListingEntity);
    }

    @Override
    public ResponseEntity<Double> getProductListingRating(String productListingId)
    {
        Optional<ProductListingEntity> productListingEntity = productListingRepository.findById(productListingId);
        if (productListingEntity.isPresent())
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
    public CheckStockResponse checkProductStock(List<CartProduct> cartProducts){

        CheckStockResponse checkStockResponse = new CheckStockResponse();
        Iterator<CartProduct> iterator = cartProducts.iterator();
        List<UnavailableStock> unavailableStocks = new ArrayList<>();

        //Getting product listing for each product in the listing table
        int flag = 0;
        while (iterator.hasNext()){
            CartProduct cartProduct = iterator.next();
            Optional<ProductListingEntity> productListingEntity = productListingRepository.findByProductIdAndMerchantId(
                    cartProduct.getProductId(),cartProduct.getMerchantId());

            if(productListingEntity.get().getQuantity() < cartProduct.getQuantity()){
                flag = 1;
                unavailableStocks.add(new UnavailableStock(cartProduct.getProductName(),productListingEntity.get().getQuantity()));

            }
        }

        if(flag == 1){
            checkStockResponse.setStatus(false);
            checkStockResponse.setUnavailableStock(unavailableStocks);
        }

        if(flag == 0){
            checkStockResponse.setStatus(true);
            checkStockResponse.setUnavailableStock(unavailableStocks);

        }

        return checkStockResponse;
    }


    @Override
    public ResponseEntity<Integer> getStock(String productListingId)
    {
        Optional<ProductListingEntity> productListingEntity = productListingRepository.findById(productListingId);

        if (productListingEntity.isPresent())
        {
            return new ResponseEntity<Integer>(productListingEntity.get().getQuantity(), HttpStatus.OK);
        }
        else
            {
            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public void updateStock(List<CartProduct> cartProducts) {

        Iterator<CartProduct> iterator = cartProducts.iterator();
        Iterator<CartProduct> iterator1 = cartProducts.iterator();

        while (iterator.hasNext()){
            CartProduct cartProduct = iterator.next();

            Optional<ProductListingEntity> productListingEntity = productListingRepository.findByProductIdAndMerchantId(cartProduct.getProductId(),cartProduct.getMerchantId());
            System.out.println(productListingEntity.get().getQuantity());

            //Assuming ki listing exists tabhi order kar paa rha
            ProductListingEntity productListingEntity1 = productListingEntity.get();

            productListingEntity1.setQuantity(productListingEntity1.getQuantity() - cartProduct.getQuantity());

           // productListingRepository.deleteById(productListingEntity.get().getProductListingId());
            productListingRepository.save(productListingEntity1);
        }

        // Updating the Product Stock
        ProductStockUpdateClient productStockUpdateClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ProductStockUpdateClient.class))
                .logLevel(Logger.Level.FULL)
                .target(ProductStockUpdateClient.class,"http://172.16.20.119:8081/product/update");

        while (iterator1.hasNext()){
            CartProduct cartProduct = iterator1.next();
            productStockUpdateClient.updateStock(cartProduct.getProductId(),-cartProduct.getQuantity());
        }


    }

    @Override
    @Transactional
    public ResponseEntity<String> increaseProductStock(String productListingId, int offset)
    {
        Optional<ProductListingEntity> productListingEntity = productListingRepository.findById(productListingId);

        if (productListingEntity.isPresent())
        {
            productListingEntity.get().setQuantity(productListingEntity.get().getQuantity() + offset);
            productListingRepository.deleteById(productListingId);
            productListingRepository.save(productListingEntity.get());

            //updates default price and mId
            setDefaultMerchantIdAndDefaultPrice(productListingEntity.get().getProductId());



            ProductStockUpdateClient productStockUpdateClient = Feign.builder()
                    .client(new OkHttpClient())
                    .encoder(new GsonEncoder())
                    .decoder(new GsonDecoder())
                    .logger(new Slf4jLogger(ProductStockUpdateClient.class))
                    .logLevel(Logger.Level.FULL)
                    .target(ProductStockUpdateClient.class, "http://172.16.20.119:8081/product/update");

            productStockUpdateClient.updateStock(productListingEntity.get().getProductId(),offset);


            return new ResponseEntity<String>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<GetMerchantsbyPidResponse> findMerchantsbyPid(String productId)
    {
        List<ProductListingEntity> productListingEntityList = productListingRepository.findByProductId(productId);
        System.out.println(productListingEntityList.size());
        List<GetMerchantsbyPidResponse> result = new ArrayList<>();
        Iterator<ProductListingEntity> iterator = productListingEntityList.iterator();

        //finding all the Name of the merchants

        while (iterator.hasNext())
        {

            GetMerchantsbyPidResponse getMerchantsbyPidResponse = new GetMerchantsbyPidResponse();
            ProductListingEntity productListingEntity = iterator.next();
            //Adding all the required information to the response DTO
            getMerchantsbyPidResponse.setMerchantId(productListingEntity.getMerchantId());
            getMerchantsbyPidResponse.setCost(productListingEntity.getPrice());
            getMerchantsbyPidResponse.setProductRating(productListingEntity.getProductListingRating());
            getMerchantsbyPidResponse.setProductId(productId);
            getMerchantsbyPidResponse.setColor(productListingEntity.getColor());
            getMerchantsbyPidResponse.setSize(productListingEntity.getSize());
            getMerchantsbyPidResponse.setTheme(productListingEntity.getTheme());

            Optional<MerchantEntity> merchantEntity = merchantRepository.findById(productListingEntity.getMerchantId());
            if (merchantEntity.isPresent())
            {
                getMerchantsbyPidResponse.setMerchantName(merchantEntity.get().getFirstName());
            }

            result.add(getMerchantsbyPidResponse);

        }

        Iterator<GetMerchantsbyPidResponse> itr = result.iterator();
        TreeMap<Double, GetMerchantsbyPidResponse> treeMap = new TreeMap<>();

        while (itr.hasNext()) {
            GetMerchantsbyPidResponse getMerchantsbyPidResponse = itr.next();
            String mid = getMerchantsbyPidResponse.getMerchantId();
            String pid = getMerchantsbyPidResponse.getProductId();

            //System.out.println(pid+":"+mid);

            Optional<ProductListingEntity> productListingEntityOptional = productListingRepository.findByProductIdAndMerchantId(pid, mid);

            double merchantStock = 0;
            double cost = 0;
            double productRating = 0;
            if (productListingEntityOptional.isPresent())
            {
                merchantStock = productListingEntityOptional.get().getQuantity() * 0.1;
                cost = productListingEntityOptional.get().getPrice() * 0.3 * -1;
                productRating = productListingEntityOptional.get().getProductListingRating() * 0.2;
            }

            Optional<MerchantEntity> merchantEntityOptional = merchantRepository.findById(mid);

            double merchantRating = 0;
            double productSold = 0;
            if (merchantEntityOptional.isPresent())
            {
                merchantRating = merchantEntityOptional.get().getMerchantRating() * 0.2;
                productSold = merchantEntityOptional.get().getTotalProductSold() * 0.1;
            }

            ProductClient productClient = Feign.builder()
                    .client(new OkHttpClient())
                    .encoder(new GsonEncoder())
                    .decoder(new GsonDecoder())
                    .logger(new Slf4jLogger(ProductClient.class))
                    .logLevel(Logger.Level.FULL)
                    .target(ProductClient.class, "http://172.16.20.119:8081/product");

            ProductDTO productDTO = productClient.getProductByProductId(pid);
            double productStock = productDTO.getTotalStock() * 0.1;
            double sum = merchantRating + merchantStock + productRating + cost + productSold + productStock;
            treeMap.put(new Double(sum), getMerchantsbyPidResponse);
        }

        ArrayList<GetMerchantsbyPidResponse> sortedResult = new ArrayList<>();
        for (Map.Entry<Double, GetMerchantsbyPidResponse> entry : treeMap.entrySet())
        {
            sortedResult.add(entry.getValue());
        }
        return sortedResult;

    }

    @Override
    @Transactional
    public ResponseEntity<String> updateProductListingRating(double currentRating, String productListingId) {
        Optional<ProductListingEntity> productListingEntity = productListingRepository.findById(productListingId);
        if (productListingEntity.isPresent()) {
            double productListingRating = productListingEntity.get().getProductListingRating();
            int numberOfRatings = productListingEntity.get().getNumberOfRatings();
            double newRating = (productListingRating * numberOfRatings) / (numberOfRatings + 1);
            productListingEntity.get().setProductListingRating(newRating);
            productListingRepository.deleteById(productListingId);
            productListingRepository.save(productListingEntity.get());


            //updates default price and mId
            setDefaultMerchantIdAndDefaultPrice(productListingEntity.get().getProductId());


            return new ResponseEntity<String>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public void setDefaultMerchantIdAndDefaultPrice(String productId)
    {
        List<GetMerchantsbyPidResponse> listOfMerchants = findMerchantsbyPid(productId);
        System.out.println(listOfMerchants.size());
        ProductSaveClient productClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ProductSaveClient.class))
                .logLevel(Logger.Level.FULL)
                .target(ProductSaveClient.class, "http://172.16.20.119:8081/product/updateproduct");
        productClient.updateMerchantPrice(listOfMerchants.get(0).getProductId(),listOfMerchants.get(0).getMerchantId(),listOfMerchants.get(0).getCost());
    }
}
