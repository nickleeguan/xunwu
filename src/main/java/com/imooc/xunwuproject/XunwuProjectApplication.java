package com.imooc.xunwuproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class XunwuProjectApplication {

	@GetMapping("/hello")
	public String hello(){
	    return "Hello";
    }

	public static void main(String[] args) {
		SpringApplication.run(XunwuProjectApplication.class, args);
	}
}
