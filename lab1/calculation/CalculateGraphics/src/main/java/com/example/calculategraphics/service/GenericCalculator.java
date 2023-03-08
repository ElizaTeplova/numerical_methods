package com.example.calculategraphics.service;

import com.example.calculategraphics.model.Coordinate;

import java.util.List;

public interface GenericCalculator {

    List<Coordinate> calculateFunction();

    List<Coordinate> calculateDerivativeOfOne();

    List<Coordinate> calculateDerivativeOfTwo();
}
