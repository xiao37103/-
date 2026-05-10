package com.wjx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Gao {
    private double longitude;
    private double latitude;
    private double fitness;
    private double comfort_norm;
}
