package com.softper.ts.services;

import com.softper.ts.models.Vehicle;
import com.softper.ts.resources.comunications.VehicleResponse;
import com.softper.ts.resources.inputs.VehicleInput;

public interface IVehicleService extends ICrudService<Vehicle> {
    VehicleResponse findVehiclesByDriverId(int driverId);
    VehicleResponse addVehicleByUserId(int driverId, VehicleInput vehicleInput);
    VehicleResponse findAllVehicles();
    VehicleResponse findVehicleById(int vehicleId);
}
