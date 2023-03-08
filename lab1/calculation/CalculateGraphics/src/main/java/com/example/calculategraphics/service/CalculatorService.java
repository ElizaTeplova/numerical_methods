package com.example.calculategraphics.service;


import com.example.calculategraphics.model.Coordinate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorService implements GenericCalculator {

    private List<Coordinate> function;
    private int steps;
    private double dt;
    private double start;
    private double end;


    @Override
    public List<Coordinate> calculateFunction() {

        for (int i = 0; i < steps; i++) {
            function.add(function(start + i * dt));
        }

        return function;
    }

    @Override
    public List<Coordinate> calculateDerivativeOfOne() {
        return null;
    }

    @Override
    public List<Coordinate> calculateDerivativeOfTwo() {
        return null;
    }

    private Coordinate function(double x) {
        return new Coordinate(x, (Math.sqrt(x - 1) * (3 * x + 2)) / (4 * x * x));
    }

    public void calcDt(double start, double end, int steps) {
        this.start = start;
        this.end = end;
        this.steps = steps;


        dt = (end - start) / steps;
    }
}
