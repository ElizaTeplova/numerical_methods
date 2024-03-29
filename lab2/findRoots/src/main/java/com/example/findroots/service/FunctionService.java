package com.example.findroots.service;


import com.example.findroots.models.Coordinate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FunctionService implements RootFinding, IntegralCalculating {

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
        } while (it < numberOfIterations && Math.abs(f2.getY()) >= eps && Math.abs(x0 - x1) >= eps);

        return x2;
    }

    @Override
    public double newtonsMethod(double xGuessed) {
        int numberOfIterations = 100, it = 0;
        dt = 0.01;
        double eps = 10e-3;
        double xNext;
        double xCurrent = xGuessed;
        Coordinate f, fDerivative;

        do {

            f = analyticFunction(xCurrent);
            fDerivative = fifthFormulaLab1(xCurrent);
            xNext = xCurrent - f.getY() / fDerivative.getY();
            System.out.println("it: " + it + " xNext: " + xNext + " xCurrent: " + xCurrent);
            xCurrent = xNext;
            it++;
        } while (
//                Math.abs(xNext - xCurrent) >= eps &&
                Math.abs(analyticFunction(xNext).getY()) >= eps &&
                        it < numberOfIterations);
        return xNext;
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

    private Coordinate fifthFormulaLab1(double x) {
        return new Coordinate(x, (analyticFunction(x + dt).getY() - analyticFunction(x - dt).getY()) / (2 * dt));
    }

    public void calcDt(double start, double end, int steps) {
        this.start = start;
        this.end = end;
        this.steps = steps;

        dt = (end - start) / steps;

        log.info("\nstart: " + start + "\nend: " + end + " \nsteps: " + steps + "\ndt: " + dt);
    }


    private Coordinate analyticFunctionLab3(double x) {
//        return new Coordinate(x, Math.pow(x, 3) / (1 + x));
        return new Coordinate(x, 1);
    }

    @Override
    public double rectangle(double a, double b) {
        steps = 100;
        calcDt(a, b, steps);
        double area = 0;
        double xCurrent = a;

        do {
            area += analyticFunctionLab3(xCurrent).getY();
            xCurrent += dt;
        } while (xCurrent < b);
        area *= dt;
        return area;
    }

    @Override
    public double trapezoid(double a, double b) {
        steps = 100;
        calcDt(a, b, steps);
        double area = 0;
        double xCurrent = a;

        area += analyticFunctionLab3(xCurrent).getY();
        xCurrent += dt;
        do {

            area += 2 * analyticFunctionLab3(xCurrent).getY();
            xCurrent += dt;
        } while (xCurrent < b);
        area += analyticFunctionLab3(xCurrent + dt).getY();
        area *= dt / 2;

        return area;
    }

    @Override
    public double simpson(double a, double b) {
        steps = 100;
        calcDt(a, b, steps);
        double area = 0;
        double xCurrent = a;

        area += analyticFunctionLab3(xCurrent).getY();
        do {
            xCurrent += dt;
            area += 4 * analyticFunctionLab3(xCurrent).getY();
            xCurrent += dt;
            area += 2 * analyticFunctionLab3(xCurrent).getY();
        } while (xCurrent < b);
        area += analyticFunctionLab3(xCurrent + dt).getY();
        area *= dt / 3;

        return area;
    }
}
