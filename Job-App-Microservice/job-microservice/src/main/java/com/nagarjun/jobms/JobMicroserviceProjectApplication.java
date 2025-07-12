package com.nagarjun.jobms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class JobMicroserviceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobMicroserviceProjectApplication.class, args);
	}

}
