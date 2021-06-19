package com.softper.ts.servicesImp;

import com.softper.ts.exception.ResourceNotFoundException;
import com.softper.ts.models.Driver;
import com.softper.ts.models.Location;
import com.softper.ts.models.User;
import com.softper.ts.repositories.IDriverRepository;
import com.softper.ts.repositories.IUserRepository;
import com.softper.ts.resources.comunications.DriverResponse;
import com.softper.ts.resources.outputs.DriverOutput;
import com.softper.ts.services.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService implements IDriverService {

    @Autowired
    IDriverRepository driverRepository;

    @Autowired
    IUserRepository userRepository;


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
    public DriverResponse findNearDrivers(Location location) {
        return null;
    }

    @Override
    public DriverResponse findDriverById(int driverId) {
        try
        {
            Driver getDriver = driverRepository.findById(driverId).get();
            return new DriverResponse(new DriverOutput(getDriver.getPerson().getUser().getId(),getDriver.getPerson().getFirstName(),getDriver.getPerson().getLastName(),getDriver.getLicense(),getDriver.getPerson().getUser().getEmail(),getDriver.getPerson().getPersonType(),getDriver.getId()));
        }
        catch (Exception e)
        {
            return new DriverResponse("An error ocurred while getting driver: "+e.getMessage());
        }
    }


    @Override
    public DriverResponse findAllDrivers() {
        try
        {
            List<Driver> drivers = driverRepository.findAll();
            List<DriverOutput> driverOutputList = new ArrayList<>();
            for (Driver getDriver:drivers) {
                driverOutputList.add(new DriverOutput(getDriver.getPerson().getUser().getId(),getDriver.getPerson().getFirstName(),getDriver.getPerson().getLastName(),getDriver.getLicense(),getDriver.getPerson().getUser().getEmail(),getDriver.getPerson().getPersonType(),getDriver.getId()));
            }
            return new DriverResponse(driverOutputList);
        }
        catch (Exception e)
        {
            return new DriverResponse("An error ocurred while getting driver list: "+e.getMessage());
        }
    }

    @Override
    public DriverResponse findDriversByName(String name){
        try{
            List<User> users = userRepository.findDriverByName(name);
            List<DriverOutput> driverOutputList = new ArrayList<>();
            for (User getUser:users) {
                driverOutputList.add(new DriverOutput(getUser.getPerson().getDriver().getPerson().getUser().getId(),getUser.getPerson().getDriver().getPerson().getFirstName(),getUser.getPerson().getDriver().getPerson().getLastName(),getUser.getPerson().getDriver().getLicense(),getUser.getPerson().getDriver().getPerson().getUser().getEmail(),getUser.getPerson().getDriver().getPerson().getPersonType(),getUser.getPerson().getDriver().getId()));
            }
            return new DriverResponse(driverOutputList);
        }
        catch (Exception e)
        {
            return new DriverResponse("An error ocurred while getting driver list: "+e.getMessage());
        }
    }

}
