package com.example.cartAndOrder.feignClient;

import com.example.cartAndOrder.exchanges.CartProduct;
import com.example.cartAndOrder.exchanges.orderExchanges.merchantExchanges.CheckAndUpdateRequest;
import com.example.cartAndOrder.exchanges.orderExchanges.merchantExchanges.CheckStockResponse;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="merchantClient", url="http://172.16.20.110:8081")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface MerchantClient {

    @PostMapping("/productListingController/checkstock")
    CheckStockResponse checkProductStock(@RequestBody CheckAndUpdateRequest checkAndUpdateRequest);

    @PutMapping("/productListingController/updatestock")
    ResponseEntity<Boolean> updateStock(@RequestBody CheckAndUpdateRequest checkAndUpdateRequest);


}
