package com.cs.CalculatorService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @GetMapping("/add")
    public double add(@RequestParam double operand1, @RequestParam double operand2) {
        return operand1 + operand2;
    }

    @GetMapping("/subtract")
    public double subtract(@RequestParam double operand1, @RequestParam double operand2) {
        return operand1 - operand2;
    }

    @GetMapping("/multiply")
    public double multiply(@RequestParam double operand1, @RequestParam double operand2) {
        return operand1 * operand2;
    }

    @GetMapping("/divide")
    public double divide(@RequestParam double operand1, @RequestParam double operand2) {
        if (operand2 == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return operand1 / operand2;
    }
}

