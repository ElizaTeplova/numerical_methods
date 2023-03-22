package com.example.findroots.controllers;

import com.example.findroots.models.*;
import com.example.findroots.service.FunctionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FunctionController {

    private final FunctionService functionService;

    public FunctionController(FunctionService functionService) {
        this.functionService = functionService;
    }

    @GetMapping("/testInit")
    public InitialParameters initialParameters() {
        return new InitialParameters(100, -0.291, 2.983);
    }

    @PostMapping("/function")
    @Lab2(ex = 1.0, description = "1a) f(x) x = [a, b]")
    public List<Coordinate> function(@RequestBody InitialParameters initialParameters) {
        functionService.calcDt(initialParameters.getStart(), initialParameters.getEnd(), initialParameters.getSteps());
        List<Coordinate> res = functionService.calculateFunction();
//        System.out.println(res);
        return res;
    }

    @PostMapping("/dichotomy-method")
    public double findRootByDichotomyMethod(@RequestBody InitialConditionDichotomy init) {
        double res = functionService.dichotomyMethod(init.getX0(), init.getX1(), init.getNumberOfIterations());
        System.out.println(res);
        return res;
    }

    @PostMapping("/newtons-method")
    public double findRootByNewtonsMethod(@RequestBody InitialConditionNewton init) {
        init.setXGuessed(0.738);
        System.out.println(init.getXGuessed());
        double res = functionService.newtonsMethod(init.getXGuessed());
        System.out.println(res);
        return res;
    }
}
