package com.softper.ts.services;


import com.softper.ts.models.Route;
import com.softper.ts.resources.comunications.BaseResponse;

public interface IRouteService extends ICrudService<Route> {
    BaseResponse getRouteInfo(int cargoId);
    BaseResponse findAllRoutes();
    BaseResponse findRouteById(int routeId);
}
