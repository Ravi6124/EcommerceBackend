package com.example.demoSpringLoginMicroService.feignClients;


import com.example.demoSpringLoginMicroService.dto.MerchantDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MerchantClient", url="172.16.20.110:8081/merchant")
public interface MerchantClient {

    @PostMapping(value="/addMerchant")
    public ResponseEntity<String> addMerchant(@RequestBody MerchantDTO merchantDTO);
}
