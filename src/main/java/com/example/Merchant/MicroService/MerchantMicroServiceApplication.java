package com.example.Merchant.MicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MerchantMicroServiceApplication
{

	public static void main(String[] args) {
		SpringApplication.run(MerchantMicroServiceApplication.class, args);
	}

}
