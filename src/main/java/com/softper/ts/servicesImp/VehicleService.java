package com.softper.ts.servicesImp;

import com.softper.ts.exception.ResourceNotFoundException;
import com.softper.ts.models.Driver;
import com.softper.ts.models.Soat;
import com.softper.ts.models.Vehicle;
import com.softper.ts.repositories.IDriverRepository;
import com.softper.ts.repositories.ISoatRepository;
import com.softper.ts.repositories.IVehicleRepository;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.VehicleInput;
import com.softper.ts.resources.outputs.VehicleOutput;
import com.softper.ts.services.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private IVehicleRepository vehicleRepository;

    @Autowired
    private IDriverRepository driverRepository;

    @Autowired
    private ISoatRepository soatRepository;

    @Transactional
    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Optional<Vehicle> findById(Integer id) throws Exception {
        return vehicleRepository.findById(id);
    }

    @Override
    public List<Vehicle> findAll() throws Exception {
        return vehicleRepository.findAll();
    }


    @Override
    public BaseResponse findVehiclesByDriverId(int driverId) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Vehicle> vehicles = vehicleRepository.getVehiclesByDriverId(driverId);
            List<VehicleOutput> vehicleOutputList = new ArrayList<>();
            for (Vehicle v:vehicles) {
                vehicleOutputList.add(toVehicleOutput(v));
            }

            response = new BaseResponse("findVehiclesByDriverId","success",1);
            response.setVehicleOutputList(vehicleOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findVehiclesByDriverId", "An error ocurred while getting the vehicle list : "+e.getMessage(), -2);
        }
    }

    @Override
    public BaseResponse addVehicleByUserId(int driverId, VehicleInput vehicleInput) {
        BaseResponse response = new BaseResponse();
        try
        {
            Driver getDriver = driverRepository.findById(driverId)
                    .orElseThrow(()-> new ResourceNotFoundException("driver","id",driverId));

            Soat newSoat = new Soat();
            newSoat.setAssociateName(getDriver.getPerson().getFirstName()+" "+getDriver.getPerson().getLastName());
            newSoat.setEmissionDate(Calendar.getInstance().getTime());
            newSoat.setExpireDate(Calendar.getInstance().getTime());
            newSoat.setServiceType("Servicio de carga");
            

            newSoat = soatRepository.save(newSoat);

            Vehicle newVehicle = new Vehicle();
            newVehicle.setBrand(vehicleInput.getBrand());
            newVehicle.setLoadingCapacity(vehicleInput.getLoadingCapacity());
            newVehicle.setModel(vehicleInput.getModel());
            newVehicle.setFabricationYear(Calendar.getInstance().getTime());
            newVehicle.setOwnershipCard(vehicleInput.getOwnershipCard());
            newVehicle.setState("Desactivado");

            newVehicle.setDriver(getDriver);
            newVehicle.setSoat(newSoat);

            newVehicle = vehicleRepository.save(newVehicle);

            VehicleOutput newVehicleOutput = new VehicleOutput();
            newVehicleOutput.setDriver(newVehicle.getDriver().getPerson().getFirstName()+" "+newVehicle.getDriver().getPerson().getLastName());
            newVehicleOutput.setLoadingCapacity(newVehicle.getLoadingCapacity());
            newVehicleOutput.setBrand(newVehicle.getBrand());
            newVehicleOutput.setModel(newVehicle.getModel());

            response = new BaseResponse("addVehicleByUserId","success",1);
            response.setVehicleOutput(newVehicleOutput);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("addVehicleByUserId", "An error ocurred while getting the vehicle list : "+e.getMessage(), -2);
        }

    }

    @Override
    public BaseResponse findAllVehicles() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Vehicle> vehicles = vehicleRepository.findAll();
            List<VehicleOutput> vehicleOutputList = new ArrayList<>();
            for (Vehicle v:vehicles) {
                vehicleOutputList.add(toVehicleOutput(v));
            }

            response = new BaseResponse("findAllVehicles","success",1);
            response.setVehicleOutputList(vehicleOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllVehicles", "An error ocurred while getting the vehicle list : "+e.getMessage(), -2);
        }
    }

    @Override
    public BaseResponse findVehicleById(int vehicleId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Vehicle getVehicle = vehicleRepository.findById(vehicleId).get();
            
            response = new BaseResponse("findVehicleById","success",1);
            response.setVehicleOutput(toVehicleOutput(getVehicle));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findVehicleById", "An error ocurred while getting the vehicle list : "+e.getMessage(), -2);
        }
    }

    @Override
    public BaseResponse assignVehicle(int vehicleId)
    {
        BaseResponse response = new BaseResponse();
        try{
            Vehicle getVehicle = vehicleRepository.findById(vehicleId).get();
            getVehicle.setState("Activado");

            List<Vehicle> getVehicleList = vehicleRepository.getVehiclesByDriverId(getVehicle.getDriver().getId());
            for (Vehicle v:getVehicleList) {
                v.setState("Desactivado");
            }

            vehicleRepository.saveAll(getVehicleList);

            getVehicle.setState("Activado");

            getVehicle = vehicleRepository.save(getVehicle);
            
            response = new BaseResponse("assignVehicle","success",1);
            response.setVehicleOutput(toVehicleOutput(getVehicle));
            return response;
        } catch (Exception e){
            return new BaseResponse("assignVehicle", "An error ocurred while getting the vehicle list : "+e.getMessage(), -2);
        }
    }

    @Override
    public BaseResponse revokeVehicle(int vehicleId)
    {
        BaseResponse response = new BaseResponse();
        try{
            Vehicle getVehicle = vehicleRepository.findById(vehicleId).get();
            getVehicle.setState("Desactivado");

            getVehicle = vehicleRepository.save(getVehicle);

            response = new BaseResponse("revokeVehicle","success",1);
            response.setVehicleOutput(toVehicleOutput(getVehicle));
            return response;
        } catch(Exception e){
            return new BaseResponse("revokeVehicle", "An error ocurred while getting the vehicle list : "+e.getMessage(), -2);

        }

    }

    private VehicleOutput toVehicleOutput(Vehicle v) {
        VehicleOutput newVehicleOutput = new VehicleOutput();
        newVehicleOutput.setId(v.getId());
        newVehicleOutput.setDriver(v.getDriver().getPerson().getFirstName()+" "+v.getDriver().getPerson().getLastName());
        newVehicleOutput.setModel(v.getModel());
        newVehicleOutput.setBrand(v.getBrand());
        newVehicleOutput.setLoadingCapacity(v.getLoadingCapacity());
        newVehicleOutput.setState(v.getState());

        return newVehicleOutput;
    }

}
