package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.DriverResponse;
import com.softper.ts.servicesImp.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/drivers")
public class DriversController {

    @Autowired
    private DriverService driverService;

    @GetMapping
    public ResponseEntity<DriverResponse> findAllDrivers()
    {
        DriverResponse result = driverService.getAllDrivers();

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<DriverResponse> findDriverById(@PathVariable(value = "driverId") int driverId)
    {
        DriverResponse result = driverService.findDriverById(driverId);

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
