package com.example.demoSpringLoginMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableJpaRepositories
public class DemoSpringLoginMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringLoginMicroServiceApplication.class, args);
	}

}
