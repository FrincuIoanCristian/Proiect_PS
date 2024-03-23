package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

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
