package com.example.findroots.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InitialConditionNewton {
    private double xGuessed;
    private double x0;
    private double x1;
}