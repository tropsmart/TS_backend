package com.softper.ts.services;

import com.softper.ts.models.Service;
import com.softper.ts.resources.comunications.BaseResponse;

public interface IServiceService extends ICrudService<Service> {
    BaseResponse findSomeServiceByDriverId(int driverId);
    BaseResponse findServicesByDriverId(int driverId);
    BaseResponse findAllServices();
    BaseResponse createService(int driverId);
}
