package com.softper.ts.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleOutput {
    private int id;
    private String driver;
    private String model;
    private String brand;
    private double loadingCapacity;
    private String state;
}
