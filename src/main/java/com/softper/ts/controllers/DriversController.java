package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.servicesImp.DriverService;
import com.softper.ts.servicesImp.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/drivers")
public class DriversController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllDrivers()
    {
        BaseResponse result = driverService.findAllDrivers();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<BaseResponse> findDriverById(@PathVariable(value = "driverId") int driverId)
    {
        BaseResponse result = driverService.findDriverById(driverId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{driverId}/reviews")
    public ResponseEntity<BaseResponse> findReviewsByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        BaseResponse result = reviewService.findReviewsByDriverId(driverId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/param/{name}")
    public ResponseEntity<BaseResponse> findDriversByName(@PathVariable(value = "name")String name)
    {
        BaseResponse result = driverService.findDriversByName(name);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
