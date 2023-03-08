package com.example.calculategraphics.REST.controllers;


import com.example.calculategraphics.model.Coordinate;
import com.example.calculategraphics.model.InitialParameters;
import com.example.calculategraphics.service.CalculatorService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class FunctionController {
    private final CalculatorService calculatorService;

    public FunctionController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/")
    public String hello() {
        return "http://localhost:1337/swagger-ui.html";
    }

    @PostMapping()
    public List<Coordinate> calculateFunction(@RequestBody InitialParameters initialParameters) {
        calculatorService.calcDt(initialParameters.getStart(), initialParameters.getEnd(), initialParameters.getSteps());

        List<Coordinate> function = calculatorService.calculateFunction();

        return function;
    }

}

