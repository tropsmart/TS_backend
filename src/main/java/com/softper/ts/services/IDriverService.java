package com.softper.ts.services;

import com.softper.ts.models.Driver;
import com.softper.ts.models.Location;
import com.softper.ts.resources.comunications.DriverResponse;

public interface IDriverService extends ICrudService<Driver> {
    DriverResponse findNearDrivers(Location location);
    DriverResponse findDriverById(int driverId);
    DriverResponse findDriverByUserId(int userId);
    DriverResponse findAllDrivers();
}
