package com.example.findroots.controllers;

import com.example.findroots.models.InitialArea;
import com.example.findroots.service.FunctionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lab3")
public class IntegralController {

    private final FunctionService functionService;

    public IntegralController(FunctionService functionService) {
        this.functionService = functionService;
    }

    @PostMapping("/rectangle")
    public double rectangleMethod(@RequestBody InitialArea area) {
        double res = functionService.rectangle(area.getA(), area.getB());
        System.out.println(res);
        return res;
    }


    @PostMapping("/trapezoid")
    public double trapezoidMethod(@RequestBody InitialArea area) {
        double res = functionService.trapezoid(area.getA(), area.getB());
        System.out.println(res);
        return res;
    }

    @PostMapping("/simpson")
    public double simpsonMethod(@RequestBody InitialArea area) {
        double res = functionService.simpson(area.getA(), area.getB());
        System.out.println(res);
        return res;
    }

}
