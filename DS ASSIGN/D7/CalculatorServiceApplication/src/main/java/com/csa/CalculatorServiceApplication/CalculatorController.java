package com.csa.CalculatorServiceApplication;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/distributed/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public double add(@RequestParam double operand1, @RequestParam double operand2) {
        return calculatorService.add(operand1, operand2);
    }

    @GetMapping("/subtract")
    public double subtract(@RequestParam double operand1, @RequestParam double operand2) {
        return calculatorService.subtract(operand1, operand2);
    }

    @GetMapping("/multiply")
    public double multiply(@RequestParam double operand1, @RequestParam double operand2) {
        return calculatorService.multiply(operand1, operand2);
    }

    @GetMapping("/divide")
    public double divide(@RequestParam double operand1, @RequestParam double operand2) {
        return calculatorService.divide(operand1, operand2);
    }
}

