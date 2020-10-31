package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.VehicleResponse;
import com.softper.ts.resources.inputs.VehicleInput;
import com.softper.ts.servicesImp.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/vehicles")
public class VehiclesController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<VehicleResponse> findAllVehicles()
    {
        VehicleResponse result = vehicleService.findAllVehicles();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponse> findVehicleById(@PathVariable(value = "vehicleId")int vehicleId)
    {
        VehicleResponse result = vehicleService.findVehicleById(vehicleId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/drivers/{driverId}")
    public ResponseEntity<VehicleResponse> addVehicleByUserId(@PathVariable(value = "driverId")int driverId, @RequestBody VehicleInput vehicleInput)
    {
        VehicleResponse result = vehicleService.addVehicleByUserId(driverId, vehicleInput);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<VehicleResponse> findVehiclesByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        VehicleResponse result = vehicleService.findVehiclesByDriverId(driverId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
        //result success
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
