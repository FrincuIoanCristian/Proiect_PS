package com.example.restservice;

import com.example.restservice.controller.HelloWorldController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {

	@Autowired
	private HelloWorldController helloWorldController;

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}

	public void callHelloWorldController() {
		String message = helloWorldController.helloWorld();
		System.out.println(message);
	}
}
