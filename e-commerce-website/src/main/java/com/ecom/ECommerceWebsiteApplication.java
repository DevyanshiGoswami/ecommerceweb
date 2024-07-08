package com.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
//@SpringBootApplication(scanBasePackages = {"com.ecom"})
@SpringBootApplication
public class ECommerceWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceWebsiteApplication.class, args);
	}

}
