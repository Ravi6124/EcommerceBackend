package com.example.Search.MicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SearchMicroServiceApplication
{

	public static void main(String[] args) {
		SpringApplication.run(SearchMicroServiceApplication.class, args);
	}

}
