package com.csa.CalculatorServiceApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.csa.config")
public class CalculatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorServiceApplication.class, args);
	}

}
