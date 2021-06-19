package com.softper.ts.resources.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CargoInput implements Serializable {
    private double weight;
    private String description;
    private int servicePrice;
    private int serviceId;
    private Double arrivalAltitude;
    private Double arrivalLatitude;
    private Double arrivalLongitude;
    private Double departureAltitude;
    private Double departureLatitude;
    private Double departureLongitude;


    public CargoInput(double weight, String description, int servicePrice, int serviceId) {
        this.weight = weight;
        this.description = description;
        this.servicePrice = servicePrice;
        this.serviceId = serviceId;
    }
}
