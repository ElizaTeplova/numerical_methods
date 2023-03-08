package com.example.lab1.service;

import com.example.lab1.model.Coordinate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CalculatorService implements GenericCalculator {

    private List<Coordinate> function;
    private int steps;
    private double dt;
    private double start;
    private double end;


    @Override
    public List<Coordinate> calculateFunction() {
        function = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            function.add(analyticFunction(start + i * dt));
        }
//        System.out.println(function);
        return function;
    }

    @Override
    public List<Coordinate> calculateAnalyticDerivativeOfOne() {
        function = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            function.add(analyticFirstDerivativeFunction(start + i * dt));
        }
        return function;
    }

    @Override
    public List<Coordinate> calculateAnalyticDerivativeOfTwo() {
        function = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            function.add(analyticSecondDerivativeFunction(start + i * dt));
        }
        return function;
    }

    @Override
    public List<Coordinate> calculateAnalyticDerivativeOfThree() {
        function = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            function.add(analyticThirdDerivativeFunction(start + i * dt));
        }
        return function;
    }

    private Coordinate analyticFunction(double x) {
        return new Coordinate(x, (Math.sqrt(x - 1) * (3 * x + 2)) / (4 * x * x));
    }

    private Coordinate analyticFirstDerivativeFunction(double x) {
        return new Coordinate(x, (8 - 3 * x * x * x) / (8 * x * x * x * Math.sqrt(x - 1)));
    }

    private Coordinate analyticSecondDerivativeFunction(double x) {
        return new Coordinate(x, (9 * Math.pow(x, 3) - 6 * x * x - 56 * x + 48) /
                (16 * Math.pow(x, 4) * Math.pow(x - 1, 1.5)));
    }

    private Coordinate analyticThirdDerivativeFunction(double x) {
        return new Coordinate(x, (-45 * Math.pow(x, 4) + 60 * Math.pow(x, 3) + 480 * x * x - 864 * x + 384) /
                (32 * Math.pow(x, 5) * Math.pow(x - 1, 5.0 / 2)));
    }

    @Override
    public List<Coordinate> calculateNumericRightFirstDerivative() {
        function = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            function.add(secondFormulaLab1(start + i * dt));
        }
        return function;
    }

    @Override
    public List<Coordinate> calculateNumericCentralFirstDerivative() {
        function = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            function.add(fifthFormulaLab1(start + i * dt));
        }
        return function;
    }

    @Override
    public List<Coordinate> calculateNumericSecondDerivative() {
        function = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            function.add(ninthFormulaLab1(start + i * dt));
        }
        return function;
    }

    @Override
    public List<Coordinate> calculateNumericThirdDerivative() {
        function = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            function.add(tenthFormulaLab1(start + i * dt));
        }
        return function;

    }

    private Coordinate secondFormulaLab1(double x) {
        return new Coordinate(x, (analyticFunction(x + dt).getY() - analyticFunction(x).getY()) / dt);
    }

    private Coordinate fifthFormulaLab1(double x) {
        return new Coordinate(x, (analyticFunction(x + dt).getY() - analyticFunction(x - dt).getY()) / (2 * dt));
    }

    private Coordinate ninthFormulaLab1(double x) {
        return new Coordinate(x, (analyticFunction(x + dt).getY() - 2 * analyticFunction(x).getY() + analyticFunction(x - dt).getY()) / (dt * dt));
    }

    private Coordinate tenthFormulaLab1(double x) {
        return new Coordinate(x, (analyticFunction(x + 2 * dt).getY() - 2 * analyticFunction(x + dt).getY() + 2 * analyticFunction(x - dt).getY() - analyticFunction(x - 2 * dt).getY()) / (2 * dt * dt * dt));
    }

    public  Coordinate newtonMethod(double x0) {
        int iteration = 0;
        double eps = 0.00000000001;
        Coordinate root;
        root = new Coordinate(x0, analyticFunction(x0).getY());
        while (iteration < 100 || root.getY() <= eps) {
            log.info("it: " + iteration + "  " + root);
            root.setX(root.getX() - analyticFunction(root.getX()).getY() / analyticFirstDerivativeFunction(root.getX()).getY());
            root.setY(Math.abs(-analyticFunction(root.getX()).getY() / analyticFirstDerivativeFunction(root.getX()).getY()));

            iteration++;
        }
        if (root.getY() <= 0.00000000001) {
            log.info("Найден предполагаемый корень: " + root);
        } else  {
            log.info("Найден корень по завершении итераций: " + root);
        }

        return root;
    }
    public void calcDt(double start, double end, int steps) {
        this.start = start;
        this.end = end;
        this.steps = steps;

        dt = (end - start) / steps;

        log.info("\nstart: " + start + "\nend: " + end + " \nsteps: " + steps + "\ndt: " + dt);
    }

}
