package com.softper.ts.servicesImp;

import com.softper.ts.models.Driver;
import com.softper.ts.models.Location;
import com.softper.ts.repositories.IDriverRepository;
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
            return new DriverResponse(new DriverOutput(getDriver.getId(),getDriver.getPerson().getFirstName(),getDriver.getPerson().getLastName(),getDriver.getLicense()));
        }
        catch (Exception e)
        {
            return new DriverResponse("An error ocurred while getting driver: "+e.getMessage());
        }
    }

    @Override
    public DriverResponse getAllDrivers() {
        try
        {
            List<Driver> drivers = driverRepository.findAll();
            List<DriverOutput> driverOutputList = new ArrayList<>();
            for (Driver d:drivers) {
                driverOutputList.add(new DriverOutput(d.getId(),d.getPerson().getFirstName(),d.getPerson().getLastName(),d.getLicense()));
            }
            return new DriverResponse(driverOutputList);
        }
        catch (Exception e)
        {
            return new DriverResponse("An error ocurred while getting driver list: "+e.getMessage());
        }
    }
}
