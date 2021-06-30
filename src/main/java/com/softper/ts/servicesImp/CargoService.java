package com.softper.ts.servicesImp;

import com.softper.ts.exception.ResourceNotFoundException;
import com.softper.ts.models.*;
import com.softper.ts.repositories.*;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.CargoInput;
import com.softper.ts.resources.outputs.CargoOutput;
import com.softper.ts.resources.outputs.CargoOutputFixed;
import com.softper.ts.services.ICargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.models.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService implements ICargoService {

    @Autowired
    private ICargoRepository cargoRepository;

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IPriceRepository priceRepository;
    @Autowired
    private IBalanceRepository balanceRepository;
    @Autowired
    private IServiceRepository serviceRepository;
    @Autowired
    private IServiceRequestRepository serviceRequestRepository; 
    @Autowired
    private ILocationRepository locationRepository;




    @Override
    public Cargo save(Cargo cargo) throws Exception {
        return cargoRepository.save(cargo);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        cargoRepository.deleteById(id);
    }

    @Override
    public Optional<Cargo> findById(Integer id) throws Exception {
        return cargoRepository.findById(id);
    }

    @Override
    public List<Cargo> findAll() throws Exception {
        return cargoRepository.findAll();
    }


    @Override
    public BaseResponse findCargoesByCustomerId(int customerId) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Cargo> cargoes = cargoRepository.findCargoesByCustomerId(customerId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();
            for (Cargo c:cargoes) {
                cargoOutputList.add(toCargoOutput(c));
            }
            response = new BaseResponse("findCargoesByCustomerId","success",1);
            response.setCargoOutputList(cargoOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findCargoesByCustomerId","An error ocurred while getting cargo list: "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse addCargoByCustomerId(int customerId, CargoInput cargoInput) {
        BaseResponse response = new BaseResponse();
        try
        {
            com.softper.ts.models.Service getService = serviceRepository.findById(cargoInput.getServiceId()).get();
            Customer getCustomer = customerRepository.findById(customerId)
                    .orElseThrow(()->new ResourceNotFoundException("customer","id",customerId));
            User getUser = userRepository.findUserByPersonId(getCustomer.getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("customer","id",customerId));

            Balance getBalance = getUser.getBalance();
            if(getCustomer.getCredits() - cargoInput.getServicePrice()<0)
                return new BaseResponse("addCargoByCustomerId","You dont have enough credits",0);
            
            Price newPrice = new Price();
            newPrice.setTotalPrice((double) cargoInput.getServicePrice());
            newPrice.setTax(((double) cargoInput.getServicePrice()) * 0.19);
            newPrice.setPriceType(2);
            newPrice = priceRepository.save(newPrice);

            getBalance.setSpentMoney(getBalance.getSpentMoney() + newPrice.getTotalPrice());
            getCustomer.setCredits(getCustomer.getCredits() - newPrice.getTotalPrice());
            getBalance = balanceRepository.save(getBalance);
            getCustomer = customerRepository.save(getCustomer);

            Cargo newCargo = new Cargo();
            newCargo.setCustomer(getCustomer);
            newCargo.setService(getService);
            newCargo.setDescription(cargoInput.getDescription());
            newCargo.setWeight(cargoInput.getWeight());
            newCargo.setPrice(newPrice);
            newCargo.setCargoType("Chemicals");
            newCargo.setCargoStatus("Awaiting");
            newCargo = cargoRepository.save(newCargo);

            Location newLocation = new Location();
            newLocation.setArrivalAltitude(cargoInput.getArrivalAltitude()!=null ? cargoInput.getArrivalAltitude(): 1.0);
            newLocation.setArrivalLatitude(cargoInput.getArrivalLatitude()!=null ? cargoInput.getArrivalLatitude(): 2.0);;
            newLocation.setArrivalLongitude(cargoInput.getArrivalLatitude()!=null ? cargoInput.getArrivalLatitude(): 3.0);;
            newLocation.setDepartureAltitude(cargoInput.getDepartureAltitude()!=null ? cargoInput.getDepartureAltitude(): 4.0);
            newLocation.setDepartureLatitude(cargoInput.getDepartureLatitude()!=null ? cargoInput.getDepartureLatitude(): 5.0);
            newLocation.setDepartureLongitude(cargoInput.getDepartureLongitude()!=null ? cargoInput.getDepartureLongitude(): 6.0);
            newLocation.setRoute(getService.getRoute());
            newLocation.setCargo(newCargo);
            newLocation = locationRepository.save(newLocation);

            response = new BaseResponse("addCargoByCustomerId","success",1);
            response.setCargoOutput(toCargoOutput(newCargo));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("addCargoByCustomerId","An error ocurred while registering a cargo: "+e.getMessage(),-2);
        }

    }

    @Override
    public BaseResponse findCargoById(int cargoId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            response = new BaseResponse("findCargoById","success",1);
            response.setCargoOutput(toCargoOutput(getCargo));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findCargoById","An error ocurred while getting a cargo: "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findAllCargoes() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Cargo> cargoes = cargoRepository.findAll();
            List<CargoOutput> cargoOutputList = new ArrayList<>();
            for (Cargo c:cargoes) {
                cargoOutputList.add(toCargoOutput(c));
            }
            response = new BaseResponse("findAllCargoes","success",1);
            response.setCargoOutputList(cargoOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllCargoes","An error ocurred while getting the cargo list: "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findAllCargoesFixed() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Cargo> cargoes = cargoRepository.findAll();
            List<CargoOutput> cargoOutputList = new ArrayList<>();
            for (Cargo c:cargoes) {
                cargoOutputList.add(toCargoOutput(c));
            }
            response = new BaseResponse("findAllCargoesFixed","success",1);
            response.setCargoOutputList(cargoOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllCargoesFixed","An error ocurred while getting the cargo list: "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse setCargoDelivered(int cargoId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            User getUser = userRepository.findUserByPersonId(getCargo.getCustomer().getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("user","id",cargoId));
            Customer getCustomer = getCargo.getCustomer();
            Balance getBalance = getUser.getBalance();

            getCargo.setCargoStatus("Done");
            getCargo = cargoRepository.save(getCargo);

            response = new BaseResponse("setCargoDelivered","success",1);
            response.setCargoOutput(toCargoOutput(getCargo));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("setCargoDelivered","An error ocurred while getting the cargo list: "+e.getMessage(),-2);
        }
    }


    @Override
    public BaseResponse findCargoesByDriverId(int driverId) {
        BaseResponse response = new BaseResponse();
        try
        {
            ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();

            for (com.softper.ts.models.Service s:getServiceRequest.getServicesList()) {
                for(Cargo c:s.getCargoList())
                {
                    cargoOutputList.add(toCargoOutput(c));
                }
            }
            response = new BaseResponse("findCargoesByDriverId","success",1);
            response.setCargoOutputList(cargoOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findCargoesByDriverId","An error ocurred while getting the cargo list : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findRequestedCargoesByDriverId(int driverId) {
        BaseResponse response = new BaseResponse();
        try
        {
            ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();

            for (com.softper.ts.models.Service s:getServiceRequest.getServicesList()) {
                for(Cargo c:s.getCargoList())
                {
                    if(c.getCargoStatus().equals("Awaiting"))
                    {
                        cargoOutputList.add(toCargoOutput(c));
                    }
                }
            }
            response = new BaseResponse("findRequestedCargoesByDriverId","success",1);
            response.setCargoOutputList(cargoOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findRequestedCargoesByDriverId","An error ocurred while getting the cargo list : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findConfirmedCargoesByDriverId(int driverId) {
        BaseResponse response = new BaseResponse();
        try
        {
            ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();

            for (com.softper.ts.models.Service s:getServiceRequest.getServicesList()) {
                for(Cargo c:s.getCargoList())
                {
                    if(c.getCargoStatus().equals("In process"))
                    {
                        cargoOutputList.add(toCargoOutput(c));
                    }
                }
            }
            response = new BaseResponse("findConfirmedCargoesByDriverId","success",1);
            response.setCargoOutputList(cargoOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findConfirmedCargoesByDriverId","An error ocurred while getting the cargo list : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findFinishedCargoesByDriverId(int driverId) {
        BaseResponse response = new BaseResponse();
        try
        {
            ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();

            for (com.softper.ts.models.Service s:getServiceRequest.getServicesList()) {
                for(Cargo c:s.getCargoList())
                {
                    if(c.getCargoStatus().equals("Done"))
                    {
                        cargoOutputList.add(toCargoOutput(c));
                    }
                }
            }
            response = new BaseResponse("findFinishedCargoesByDriverId","success",1);
            response.setCargoOutputList(cargoOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findFinishedCargoesByDriverId","An error ocurred while getting the cargo list : "+e.getMessage(),-2);
        }
    }



    @Override
    public BaseResponse confirmCargoRequest(int cargoId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            User getUser = userRepository.findUserByPersonId(getCargo.getCustomer().getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("user","id",cargoId));
            Customer getCustomer = getCargo.getCustomer();
            Balance getBalance = getUser.getBalance();
            getCargo.setCargoStatus("In process");
            getCargo = cargoRepository.save(getCargo);

            response = new BaseResponse("confirmCargoRequest","success",1);
            response.setCargoOutput(toCargoOutput(getCargo));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("confirmCargoRequest","An error ocurred while getting the cargo list : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse rejectCargoById(int cargoId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            User getUser = userRepository.findUserByPersonId(getCargo.getCustomer().getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("user","id",cargoId));
            Customer getCustomer = getCargo.getCustomer();
            Balance getBalance = getUser.getBalance();
            getCargo.setCargoStatus("Rejected");
            getCargo = cargoRepository.save(getCargo);

            response = new BaseResponse("rejectCargoById","success",1);
            response.setCargoOutput(toCargoOutput(getCargo));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("rejectCargoById","An error ocurred while getting the cargo list : "+e.getMessage(),-2);
        }
    }


    public CargoOutput toCargoOutput(Cargo cargo){
        BaseResponse response = new BaseResponse();
        CargoOutput newCargoOutput = new CargoOutput();
        newCargoOutput.setId(cargo.getId());
        newCargoOutput.setWeight(cargo.getWeight());
        newCargoOutput.setCustomer(cargo.getCustomer().getPerson().getFirstName()+" "+cargo.getCustomer().getPerson().getFirstName());
        newCargoOutput.setDriver(cargo.getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+cargo.getService().getServicesRequest().getDriver().getPerson().getLastName());
        newCargoOutput.setStartTime(cargo.getService().getStartTime());
        newCargoOutput.setFinishTime(cargo.getService().getFinishTime());
        newCargoOutput.setServicePrice(cargo.getPrice().getTotalPrice());
        newCargoOutput.setDescription(cargo.getDescription());
        newCargoOutput.setCargoType(cargo.getCargoType().toString());
        newCargoOutput.setCargoStatus(cargo.getCargoStatus());

        return newCargoOutput;
    }




}
