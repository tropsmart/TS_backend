package com.softper.ts.servicesImp;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.softper.ts.config.DataUtils;
import com.softper.ts.config.Dataobj;
import com.softper.ts.exception.ResourceNotFoundException;
import com.softper.ts.models.Driver;
import com.softper.ts.models.Location;
import com.softper.ts.models.User;
import com.softper.ts.repositories.IDriverRepository;
import com.softper.ts.repositories.IUserRepository;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.outputs.DriverOutput;
import com.softper.ts.services.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DriverService implements IDriverService {

    @Autowired
    IDriverRepository driverRepository;

    @Autowired
    IUserRepository userRepository;

    DataUtils data;

    public DriverService() {
        try{
            data = new DataUtils();
        }
        catch(IOException e){
           
        }
        

    }

    @Override
    public Driver save(Driver driver) throws Exception {
        return driverRepository.save(driver);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        driverRepository.deleteById(id);
    }

    @Override
    public Optional<Driver> findById(Integer id) throws Exception {
        return driverRepository.findById(id);
    }

    @Override
    public List<Driver> findAll() throws Exception {
        return driverRepository.findAll();
    }


    @Override
    public BaseResponse findNearDrivers(Location location) {
        return null;
    }

    @Override
    public BaseResponse findDriverById(int driverId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Driver getDriver = driverRepository.findById(driverId).get();
            response = new BaseResponse("findDriverById","success",1);
            response.setDriverOutput(new DriverOutput(getDriver.getPerson().getUser().getId(),getDriver.getPerson().getFirstName(),getDriver.getPerson().getLastName(),getDriver.getLicense(),getDriver.getPerson().getUser().getEmail(),getDriver.getPerson().getPersonType(),getDriver.getId()));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findDriverById","An error ocurred while getting driver: "+e.getMessage(),-2);
        }
    }


    @Override
    public BaseResponse findAllDrivers() {

        BaseResponse response = new BaseResponse();
        try
        {
            List<Driver> drivers = driverRepository.findAll();
            List<DriverOutput> driverOutputList = new ArrayList<>();
            for (Driver getDriver:drivers) {
                driverOutputList.add(toDriverOutout(getDriver));
            }
            response = new BaseResponse("findAllDrivers","success",1);
            response.setDriverOutputList(driverOutputList);
            return response;        
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllDrivers","An error ocurred while getting driver: "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findDriversByName(String name){
        BaseResponse response = new BaseResponse();
        try{
            List<User> users = userRepository.findDriverByName(name);
            List<DriverOutput> driverOutputList = new ArrayList<>();
            for (User getUser:users) {
                driverOutputList.add(toDriverOutout(getUser.getPerson().getDriver()));
            }
            response = new BaseResponse("findDriversByName","success",1);
            response.setDriverOutputList(driverOutputList);
            return response;        
        }
        catch (Exception e)
        {
            return new BaseResponse("findDriversByName","An error ocurred while getting driver: "+e.getMessage(),-2);
        }
    }

    public DriverOutput toDriverOutout(Driver getDriver) {
        DriverOutput newDriverOutput = new DriverOutput();
        newDriverOutput.setId(getDriver.getId());
        newDriverOutput.setFirstName(getDriver.getPerson().getFirstName());
        newDriverOutput.setLastName(getDriver.getPerson().getLastName());
        newDriverOutput.setLicense(getDriver.getLicense());
        newDriverOutput.setEmail(getDriver.getPerson().getUser().getEmail());
        newDriverOutput.setRole(getDriver.getPerson().getPersonType());
        newDriverOutput.setRoleId(getDriver.getId());

        Random rand = new Random(); //instance of random class
        int n = rand.nextInt(4)+1;
        List<String> newVehicles = new ArrayList();
        for (int p = 0; p<n; p++){
            newVehicles.add(data.getData().getVehicles().get(rand.nextInt(data.getData().getVehicles().size())));
        }
        int m = rand.nextInt(4)+1;
        List<String> newLocations = new ArrayList();
        for (int r = 0; r<m; r++){
            newLocations.add(data.getData().getLocations().get(rand.nextInt(data.getData().getLocations().size())));
        }
        newDriverOutput.setVehicles(newVehicles.stream().distinct().collect(Collectors.toList()));
        newDriverOutput.setLocations(newLocations.stream().distinct().collect(Collectors.toList()));
        return newDriverOutput;
    }

}
