package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.servicesImp.CargoService;
import com.softper.ts.servicesImp.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllCustomers()
    {
        BaseResponse result = customerService.findAllCustomers();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{cargoId}")
    public ResponseEntity<BaseResponse> findCustomersById(@PathVariable(value = "cargoId") int cargoId)
    {
        BaseResponse result = customerService.findCustomerById(cargoId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/{customerId}/cargoes")
    public ResponseEntity<BaseResponse> getCargoesByCustomerId(@PathVariable(value = "customerId")int customerId)
    {
        BaseResponse result = cargoService.findCargoesByCustomerId(customerId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{customerId}/credits/{credits}")
    public ResponseEntity<BaseResponse> rechargeCreditsByCustomer(@PathVariable(value = "customerId")int customerId, @PathVariable(value = "credits")double credits)
    {
        BaseResponse result = customerService.rechargeCreditsByCustomerId(customerId, credits);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}