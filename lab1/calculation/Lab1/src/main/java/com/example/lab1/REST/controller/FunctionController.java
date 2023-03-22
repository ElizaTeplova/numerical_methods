package com.example.lab1.REST.controller;


import com.example.lab1.annotations.Lab1;
import com.example.lab1.model.Coordinate;
import com.example.lab1.model.InitialParameters;
import com.example.lab1.service.CalculatorService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/testInit")
    public InitialParameters initialParameters() {
        return new InitialParameters(5, 5, 100);
    }

    @PostMapping("/function")
    @Lab1(ex = 1.0, description = "1a) f(x) x = [a, b]")
    public List<Coordinate> function(@RequestBody InitialParameters initialParameters) {
        calculatorService.calcDt(initialParameters.getStart(), initialParameters.getEnd(), initialParameters.getSteps());
        List<Coordinate> res = calculatorService.calculateFunction();
        System.out.println(res);
        return res;
    }

    @PostMapping("/first-derivative-analytic-function")
    @Lab1(ex = 1.1, description = "1b) f'(x) analytic function")
    public  List<Coordinate> analyticFirstDerivativeFunction(@RequestBody InitialParameters initialParameters) {
        calculatorService.calcDt(initialParameters.getStart(), initialParameters.getEnd(), initialParameters.getSteps());
        List<Coordinate> function = calculatorService.calculateAnalyticDerivativeOfOne();
        return function;
    }

    @PostMapping("/second-derivative-analytic-function")
    @Lab1(ex = 1.1, description = "1b) f''(x) analytic function")
    public  List<Coordinate> analyticSecondDerivativeFunction(@RequestBody InitialParameters initialParameters) {
        calculatorService.calcDt(initialParameters.getStart(), initialParameters.getEnd(), initialParameters.getSteps());
        List<Coordinate> function = calculatorService.calculateAnalyticDerivativeOfTwo();
        return function;
    }

    @PostMapping("/third-derivative-analytic-function")
    @Lab1(ex = 1.1, description = "1b) f'''(x) analytic function")
    public  List<Coordinate> analyticThirdDerivativeFunction(@RequestBody InitialParameters initialParameters) {
        calculatorService.calcDt(initialParameters.getStart(), initialParameters.getEnd(), initialParameters.getSteps());
        List<Coordinate> function = calculatorService.calculateAnalyticDerivativeOfThree();
        return function;
    }

    @PostMapping("/first-right-derivative-numeric-function")
    @Lab1(ex = 1.2, description = "2b) right: f'(x) numeric function")
    public  List<Coordinate> calculateNumericRightFirstDerivative(@RequestBody InitialParameters initialParameters) {
        calculatorService.calcDt(initialParameters.getStart(), initialParameters.getEnd(), initialParameters.getSteps());
        List<Coordinate> function = calculatorService.calculateNumericRightFirstDerivative();
        return function;
    }

    @PostMapping("/first-central-derivative-numeric-function")
    @Lab1(ex = 1.3, description = "1g) central: f'(x) numeric function")
    public  List<Coordinate> calculateNumericCentralFirstDerivative(@RequestBody InitialParameters initialParameters) {
        calculatorService.calcDt(initialParameters.getStart(), initialParameters.getEnd(), initialParameters.getSteps());
        List<Coordinate> function = calculatorService.calculateNumericCentralFirstDerivative();
        return function;
    }

    @PostMapping("/second-derivative-numeric-function")
    @Lab1(ex = 1.4, description = "1d) f''(x) numeric function")
    public  List<Coordinate> calculateNumericSecondDerivative(@RequestBody InitialParameters initialParameters) {
        calculatorService.calcDt(initialParameters.getStart(), initialParameters.getEnd(), initialParameters.getSteps());
        List<Coordinate> function = calculatorService.calculateNumericSecondDerivative();
        return function;
    }

    @PostMapping("/third-derivative-numeric-function")
    @Lab1(ex = 1.5, description = "1e) f'''(x) numeric function")
    public  List<Coordinate> calculateNumericThirdDerivative(@RequestBody InitialParameters initialParameters) {
        calculatorService.calcDt(initialParameters.getStart(), initialParameters.getEnd(), initialParameters.getSteps());
        List<Coordinate> function = calculatorService.calculateNumericThirdDerivative();
        return function;
    }

    @PostMapping("/find-root/")
    @Lab1(ex = 2, description = "find roots")
    public Coordinate findRootByDichotomyMethod(@PathVariable double x0) {
        return calculatorService.newtonMethod(x0);
    }

}
