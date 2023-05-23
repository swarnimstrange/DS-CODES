package com.csa.CalculatorServiceApplication;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CalculatorServiceImpl implements CalculatorService {

    private final RestTemplate restTemplate;


    public CalculatorServiceImpl( RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Bean
    @Override
    public double add(double operand1, double operand2) {
        String url = "http://localhost:8080/calculator/add?operand1={operand1}&operand2={operand2}";
        return restTemplate.getForObject(url, Double.class, operand1, operand2);
    }

    @Bean
    @Override
    public double subtract(double operand1, double operand2) {
        String url = "http://localhost:8080/calculator/subtract?operand1={operand1}&operand2={operand2}";
        return restTemplate.getForObject(url, Double.class, operand1, operand2);
    }

    @Bean
    @Override
    public double multiply(double operand1, double operand2) {
        String url = "http://localhost:8080/calculator/multiply?operand1={operand1}&operand2={operand2}";
        return restTemplate.getForObject(url, Double.class, operand1, operand2);
    }

    @Bean
    @Override
    public double divide(double operand1, double operand2) {
        String url = "http://localhost:8080/calculator/divide?operand1={operand1}&operand2={operand2}";
        return restTemplate.getForObject(url, Double.class, operand1, operand2);
    }
}

