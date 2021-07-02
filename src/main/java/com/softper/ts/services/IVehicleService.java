package com.softper.ts.services;

import com.softper.ts.models.Vehicle;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.VehicleInput;

public interface IVehicleService extends ICrudService<Vehicle> {
    BaseResponse findVehiclesByDriverId(int driverId);
    BaseResponse addVehicleByUserId(int driverId, VehicleInput vehicleInput);
    BaseResponse findAllVehicles();
    BaseResponse findVehicleById(int vehicleId);
    BaseResponse assignVehicle(int vehicleId);
    BaseResponse revokeVehicle(int vehicleId);
}
