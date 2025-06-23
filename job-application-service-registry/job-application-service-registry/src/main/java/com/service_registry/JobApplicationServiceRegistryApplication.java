package com.service_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class JobApplicationServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobApplicationServiceRegistryApplication.class, args);
	}

}
