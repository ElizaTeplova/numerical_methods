package com.example.findroots.models;

import lombok.*;
import org.springframework.stereotype.Component;

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
