package com.example.cartAndOrder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
@EnableEurekaClient
@EnableKafka
public class CartAndOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartAndOrderApplication.class, args);
	}

}
