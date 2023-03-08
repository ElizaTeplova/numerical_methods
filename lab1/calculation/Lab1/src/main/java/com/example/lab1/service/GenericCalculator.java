package com.example.lab1.service;


import com.example.lab1.model.Coordinate;

import java.util.List;

public interface GenericCalculator {

    List<Coordinate> calculateFunction();
    List<Coordinate> calculateAnalyticDerivativeOfOne();
    List<Coordinate> calculateAnalyticDerivativeOfTwo();
    List<Coordinate> calculateAnalyticDerivativeOfThree();

    List<Coordinate> calculateNumericRightFirstDerivative();

    List<Coordinate> calculateNumericCentralFirstDerivative();

    List<Coordinate> calculateNumericSecondDerivative();

    List<Coordinate> calculateNumericThirdDerivative();
}