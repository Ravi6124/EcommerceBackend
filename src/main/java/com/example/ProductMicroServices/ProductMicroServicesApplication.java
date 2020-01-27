package com.example.ProductMicroServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductMicroServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMicroServicesApplication.class, args);
	}

}
