package com.softper.ts.resources.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleInput {
    private int driverId;
    private String license;
    private String brand;
    private String model;
    private double loadingCapacity;
    private String ownershipCard;
}
