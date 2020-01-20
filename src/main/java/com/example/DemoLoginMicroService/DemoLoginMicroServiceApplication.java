package com.example.DemoLoginMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoLoginMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoLoginMicroServiceApplication.class, args);
	}

}
