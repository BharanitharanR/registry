package com.batty.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component("RegistryITBean")
@ComponentScan(basePackages =  "com.batty")
@SpringBootApplication
public class ServiceITApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceITApplication.class, args);
	}

}
