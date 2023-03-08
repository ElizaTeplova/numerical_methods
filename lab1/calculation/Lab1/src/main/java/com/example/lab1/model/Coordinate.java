package com.example.lab1.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Setter
@Getter
@Component
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {

    private double x;
    private double y;
}
