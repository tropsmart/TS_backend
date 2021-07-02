package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BaseResponse;
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
    public ResponseEntity<BaseResponse> findAllVehicles()
    {
        BaseResponse result = vehicleService.findAllVehicles();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<BaseResponse> findVehicleById(@PathVariable(value = "vehicleId")int vehicleId)
    {
        BaseResponse result = vehicleService.findVehicleById(vehicleId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/drivers/{driverId}")
    public ResponseEntity<BaseResponse> addVehicleByUserId(@PathVariable(value = "driverId")int driverId, @RequestBody VehicleInput vehicleInput)
    {
        BaseResponse result = vehicleService.addVehicleByUserId(driverId, vehicleInput);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<BaseResponse> findVehiclesByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        BaseResponse result = vehicleService.findVehiclesByDriverId(driverId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("/{vehicleId}/assign")
    public ResponseEntity<BaseResponse> assignVehicle(@PathVariable(value = "vehicleId")int vehicleId)
    {
        BaseResponse result = vehicleService.assignVehicle(vehicleId);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("/{vehicleId}/revoke")
    public ResponseEntity<BaseResponse> revokeVehicle(@PathVariable(value = "vehicleId")int vehicleId)
    {
        BaseResponse result = vehicleService.revokeVehicle(vehicleId);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
