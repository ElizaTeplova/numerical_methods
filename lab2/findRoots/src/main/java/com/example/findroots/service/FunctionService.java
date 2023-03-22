package com.example.findroots.service;


import com.example.findroots.models.Coordinate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FunctionService implements RootFinding {

    private List<Coordinate> function;
    private int steps;
    private double dt;
    private double start;
    private double end;

    @Override
    public double dichotomyMethod(double x0, double x1, int numberOfIterations) {
        int it = 0;
        double x2;
        double eps = 10e-10;
        Coordinate f0, f1, f2;

        do {
            f0 = analyticFunction(x0);
            f1 = analyticFunction(x1);

            x2 = 0.5 * (x0 + x1);
            f2 = analyticFunction(x2);

            if (f0.getY() * f2.getY() < 0) {
                x1 = x2;
            } else {
                x0 = x2;
            }

            it++;
//            System.out.println("f2: " + f2.getY() + " it: " + it);
        } while (it < numberOfIterations && Math.abs(f2.getY()) >= eps);

        return x2;
    }

    @Override
    public double newtonsMethod() {
        return 0;
    }


    @Override
    public List<Coordinate> calculateFunction() {
        function = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            function.add(analyticFunction(start + i * dt));
        }
//        System.out.println(function);
        return function;
    }

    private Coordinate analyticFunction(double x) {
        return new Coordinate(x, 5 * Math.cos(x) - x * Math.sin(x) - 2 * x);
    }

    public void calcDt(double start, double end, int steps) {
        this.start = start;
        this.end = end;
        this.steps = steps;

        dt = (end - start) / steps;

        log.info("\nstart: " + start + "\nend: " + end + " \nsteps: " + steps + "\ndt: " + dt);
    }
}
