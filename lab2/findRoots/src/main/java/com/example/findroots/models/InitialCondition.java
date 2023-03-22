package com.example.findroots.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InitialCondition {
    private double x0;
    private double x1;
    private int numberOfIterations;
}
