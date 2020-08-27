package com.softper.ts.services;


import com.softper.ts.models.Route;
import com.softper.ts.resources.comunications.RouteResponse;

public interface IRouteService extends ICrudService<Route> {
    RouteResponse getRouteInfo(int cargoId);
    RouteResponse findAllRoutes();
    RouteResponse findRouteById(int routeId);
}
