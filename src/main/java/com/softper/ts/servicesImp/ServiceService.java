package com.softper.ts.servicesImp;

import com.softper.ts.models.Driver;
import com.softper.ts.models.Route;
import com.softper.ts.models.ServiceRequest;
import com.softper.ts.repositories.IDriverRepository;
import com.softper.ts.repositories.IRouteRepository;
import com.softper.ts.repositories.IServiceRepository;
import com.softper.ts.repositories.IServiceRequestRepository;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.outputs.ServiceOutput;
import com.softper.ts.services.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService implements IServiceService {

    @Autowired
    private IServiceRequestRepository serviceRequestRepository;

    @Autowired
    private IServiceRepository serviceRepository;

    @Autowired
    private IDriverRepository driverRepository;

    @Autowired
    private IRouteRepository routeRepository;


    @Override
    public BaseResponse findSomeServiceByDriverId(int driverId) {
        BaseResponse response = new BaseResponse();
        try{
            List<com.softper.ts.models.Service> services = serviceRepository.findServicesByDriverId(driverId);
            if(services.size()==0)
            {
                return this.createService(driverId);
            }
            else
            {
                ServiceOutput newServiceOutput = new ServiceOutput();
                newServiceOutput.setId(services.get(0).getId());
                newServiceOutput.setFirstName(services.get(0).getServicesRequest().getDriver().getPerson().getFirstName());
                newServiceOutput.setLastName(services.get(0).getServicesRequest().getDriver().getPerson().getLastName());
                newServiceOutput.setStartedTime(services.get(0).getStartTime());
                newServiceOutput.setFinishTime(services.get(0).getFinishTime());
                newServiceOutput.setServiceState(services.get(0).getServiceState());

                response = new BaseResponse("findSomeServiceByDriverId","success",1);
                response.setServiceOutput(newServiceOutput);
                return response;
            }
        }
        catch (Exception e)
        {
            return new BaseResponse("findSomeServiceByDriverId","An error ocurred while getting the service list : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findServicesByDriverId(int driverId) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<com.softper.ts.models.Service> services = serviceRepository.findServicesByDriverId(driverId);
            List<ServiceOutput> serviceOutputList = new ArrayList<>();
            for (com.softper.ts.models.Service s:services) {
                ServiceOutput newServiceOutput = new ServiceOutput();
                newServiceOutput.setId(services.get(0).getId());
                newServiceOutput.setFirstName(s.getServicesRequest().getDriver().getPerson().getFirstName());
                newServiceOutput.setLastName(s.getServicesRequest().getDriver().getPerson().getLastName());
                newServiceOutput.setStartedTime(s.getStartTime());
                newServiceOutput.setFinishTime(s.getFinishTime());
                newServiceOutput.setServiceState(s.getServiceState());
                serviceOutputList.add(newServiceOutput);
            }
            response = new BaseResponse("findServicesByDriverId","success",1);
            response.setServiceOutputList(serviceOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findServicesByDriverId","An error ocurred while getting the service list : "+e.getMessage(),-2);
        }

    }

    @Override
    public BaseResponse findAllServices() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<com.softper.ts.models.Service> services = serviceRepository.findAll();
            List<ServiceOutput> serviceOutputList = new ArrayList<>();
            for (com.softper.ts.models.Service s:services) {
                ServiceOutput newServiceOutput = new ServiceOutput();
                newServiceOutput.setId(services.get(0).getId());
                newServiceOutput.setFirstName(s.getServicesRequest().getDriver().getPerson().getFirstName());
                newServiceOutput.setLastName(s.getServicesRequest().getDriver().getPerson().getLastName());
                newServiceOutput.setStartedTime(s.getStartTime());
                newServiceOutput.setFinishTime(s.getFinishTime());
                newServiceOutput.setServiceState(s.getServiceState());
                serviceOutputList.add(newServiceOutput);
            }
            response = new BaseResponse("findAllServices","success",1);
            response.setServiceOutputList(serviceOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllServices","An error ocurred while getting the service list : "+e.getMessage(),-2);

        }

    }

    @Override
    public BaseResponse createService(int driverId) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<com.softper.ts.models.Service> getService = serviceRepository.findServicesByDriverId(driverId);
            if(getService.size()== 0){
                Driver getDriver = driverRepository.findById(driverId).get();
                ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
    
                Route newRoute = new Route();
                newRoute.setDistance(200.0);
                newRoute = routeRepository.save(newRoute);
    
                com.softper.ts.models.Service newService = new com.softper.ts.models.Service();
                newService.setServiceState("OnProcess");
                newService.setServicesRequest(getServiceRequest);
                newService.setRoute(newRoute);
                newService.setStartTime(Calendar.getInstance().getTime());
                newService.setFinishTime(Calendar.getInstance().getTime());
    
                newService = serviceRepository.save(newService);
    
                ServiceOutput newServiceOutput = new ServiceOutput();
                newServiceOutput.setId(newService.getId());
                newServiceOutput.setFirstName(getDriver.getPerson().getFirstName());
                newServiceOutput.setLastName(getDriver.getPerson().getLastName());
                newServiceOutput.setStartedTime(newService.getStartTime());
                newServiceOutput.setFinishTime(newService.getFinishTime());
                newServiceOutput.setServiceState(newService.getServiceState());
    
                response = new BaseResponse("createService","success",1);
                response.setServiceOutput(newServiceOutput);
                return response;        
            } else {
                response = new BaseResponse("createService","El conductor ya posee un servicio asociado",2);
                return response;
            }
            
        }
        catch (Exception e)
        {
            return new BaseResponse("createService","An error ocurred while getting the service list : "+e.getMessage(),-2);
        }
    }

    @Override
    public com.softper.ts.models.Service save(com.softper.ts.models.Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void deleteById(Integer id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public Optional<com.softper.ts.models.Service> findById(Integer id) {
        return serviceRepository.findById(id);
    }

    @Override
    public List<com.softper.ts.models.Service> findAll() {
        return serviceRepository.findAll();
    }
}