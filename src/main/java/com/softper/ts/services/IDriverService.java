package com.softper.ts.services;

import com.softper.ts.models.Driver;
import com.softper.ts.models.Location;
import com.softper.ts.resources.comunications.BaseResponse;

public interface IDriverService extends ICrudService<Driver> {
    BaseResponse findNearDrivers(Location location);
    BaseResponse findDriverById(int driverId);
    BaseResponse findAllDrivers();
    BaseResponse findDriversByName(String name);
}
