package com.softper.ts.servicesImp;

import com.softper.ts.models.Location;
import com.softper.ts.models.Route;
import com.softper.ts.repositories.ICargoRepository;
import com.softper.ts.repositories.ILocationRepository;
import com.softper.ts.repositories.IRouteRepository;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.outputs.RouteOutput;
import com.softper.ts.services.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService implements IRouteService {

    @Autowired
    IRouteRepository routeRepository;

    @Autowired
    ICargoRepository cargoRepository;

    @Autowired
    ILocationRepository locationRepository;

    @Override
    public BaseResponse getRouteInfo(int cargoId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Location getLocation = locationRepository.findLocationByCargoId(cargoId);

            double distance = Math.sqrt(Math.pow(getLocation.getDepartureLongitude()-
                    getLocation.getDepartureLatitude(),2)+Math.pow(getLocation.getArrivalLongitude()-
                    getLocation.getArrivalLatitude(),2));

            int drivingTime = (int)(distance/80);

            RouteOutput newRouteOutput = new RouteOutput();
            newRouteOutput.setDepartureLocation(getLocation.getDepartureLatitude()+" "+getLocation.getDepartureLongitude()
                    +" "+getLocation.getDepartureAltitude());
            newRouteOutput.setArrivalLocation(getLocation.getArrivalLatitude()+" "+getLocation.getArrivalLongitude()
                    +" "+getLocation.getArrivalAltitude());
            newRouteOutput.setDistance(distance);
            newRouteOutput.setEstimedTime(drivingTime);

            response = new BaseResponse("getRouteInfo","success",1);
            response.setRouteOutput(newRouteOutput);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("getRouteInfo", "An error ocurred while getting the route info : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findAllRoutes() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Route> routeList = routeRepository.findAll();
            List<RouteOutput> routeOutputList = new ArrayList<>();
            for (Route r:routeList) {

                Location getLocation = locationRepository.findLocationByRouteId(r.getId());

                double distance = Math.sqrt(Math.pow(getLocation.getDepartureLongitude()-
                        getLocation.getDepartureLatitude(),2)+Math.pow(getLocation.getArrivalLongitude()-
                        getLocation.getArrivalLatitude(),2));

                int drivingTime = (int)(distance/80);

                RouteOutput newRouteOutput = new RouteOutput();
                newRouteOutput.setDepartureLocation(getLocation.getDepartureLatitude()+" "+getLocation.getDepartureLongitude()
                        +" "+getLocation.getDepartureAltitude());
                newRouteOutput.setArrivalLocation(getLocation.getArrivalLatitude()+" "+getLocation.getArrivalLongitude()
                        +" "+getLocation.getArrivalAltitude());
                newRouteOutput.setDistance(distance);
                newRouteOutput.setEstimedTime(drivingTime);

                routeOutputList.add(newRouteOutput);
            }
            response = new BaseResponse("findAllRoutes","success",1);
            response.setRouteOutputList(routeOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllRoutes", "An error ocurred while getting the route info : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findRouteById(int routeId) {
        BaseResponse response = new BaseResponse();
        try {
            Route getRoute = routeRepository.findById(routeId).get();
            Location getLocation = locationRepository.findLocationByRouteId(getRoute.getId());
            double distance = Math.sqrt(Math.pow(getLocation.getDepartureLongitude()-
                    getLocation.getDepartureLatitude(),2)+Math.pow(getLocation.getArrivalLongitude()-
                    getLocation.getArrivalLatitude(),2));

            int drivingTime = (int)(distance/80);

            RouteOutput newRouteOutput = new RouteOutput();
            newRouteOutput.setDepartureLocation(getLocation.getDepartureLatitude()+" "+getLocation.getDepartureLongitude()
                    +" "+getLocation.getDepartureAltitude());
            newRouteOutput.setArrivalLocation(getLocation.getArrivalLatitude()+" "+getLocation.getArrivalLongitude()
                    +" "+getLocation.getArrivalAltitude());
            newRouteOutput.setDistance(distance);
            newRouteOutput.setEstimedTime(drivingTime);

            response = new BaseResponse("findRouteById","success",1);
            response.setRouteOutput(newRouteOutput);
            return response;        }
        catch (Exception e)
        {
            return new BaseResponse("findRouteById", "An error ocurred while getting the route info : "+e.getMessage(),-2);
        }
    }

    @Override
    public Route save(Route route) throws Exception {
        return routeRepository.save(route);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        routeRepository.deleteById(id);
    }

    @Override
    public Optional<Route> findById(Integer id){
        return routeRepository.findById(id);
    }

    @Override
    public List<Route> findAll() throws Exception {
        return routeRepository.findAll();
    }
}
