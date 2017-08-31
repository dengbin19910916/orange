package com.geek.orange.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Value("${from}")
	private String from;

	@GetMapping("/from")
	public String hello() {
	    return "hello, " + from + "!";
    }

	@Value("${passport.image.directory}")
	private String directory;

	@GetMapping("/directory")
	public String directory() {
		return "hello, " + directory + "!";
	}
}
