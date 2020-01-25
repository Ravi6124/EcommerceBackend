package com.example.demoSpringLoginMicroService.feignClients;

import com.example.demoSpringLoginMicroService.dto.CustomerDto;
import com.example.demoSpringLoginMicroService.dto.SwapCartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AddToCartOrderClient", url="172.16.20.98:8087/cart")
public interface AddToCartOrderClient {

    @PutMapping("/swapcarts")
    public boolean swapCarts(@RequestBody SwapCartDTO swapCartDTO);
}
