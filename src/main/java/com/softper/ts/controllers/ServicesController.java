package com.softper.ts.controllers;

import com.softper.ts.models.ServiceRequest;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.servicesImp.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/services")
public class ServicesController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllServices()
    {
        BaseResponse result = serviceService.findAllServices();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/drivers/{driverId}")
    public ResponseEntity<BaseResponse> addServiceByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        BaseResponse result = serviceService.createService(driverId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/drivers/{driverId}/some")
    public ResponseEntity<BaseResponse> findServiceByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        BaseResponse result = serviceService.findSomeServiceByDriverId(driverId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<BaseResponse> findServicesByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        BaseResponse result = serviceService.findServicesByDriverId(driverId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
