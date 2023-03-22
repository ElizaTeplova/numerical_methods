package com.example.findroots.service;

import com.example.findroots.models.Coordinate;

import java.util.List;

public interface RootFinding {

    List<Coordinate> calculateFunction();

    double dichotomyMethod(double a, double b, int numberOfIterations);

    double newtonsMethod(double xGuessed);

}
