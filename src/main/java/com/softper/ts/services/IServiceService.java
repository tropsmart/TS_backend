package com.softper.ts.services;

import com.softper.ts.models.Service;
import com.softper.ts.resources.comunications.ServiceResponse;

public interface IServiceService extends ICrudService<Service> {
    ServiceResponse findServicesByDriverId(int driverId);
    ServiceResponse findAllServices();
    ServiceResponse createService(int driverId);
}
