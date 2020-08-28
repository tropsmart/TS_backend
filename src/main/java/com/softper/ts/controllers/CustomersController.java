package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.CustomerResponse;
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

    @GetMapping
    public ResponseEntity<CustomerResponse> findAllCustomers()
    {
        CustomerResponse result = customerService.findAllCustomers();

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{cargoId}")
    public ResponseEntity<CustomerResponse> findCustomersById(@PathVariable(value = "cargoId") int cargoId)
    {
        CustomerResponse result = customerService.findCustomerById(cargoId);

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{customerId}/credits/{credits}")
    public ResponseEntity<CustomerResponse> rechargeCreditsByCustomer(@PathVariable(value = "customerId")int customerId, @PathVariable(value = "credits")double credits)
    {
        CustomerResponse result = customerService.rechargeCreditsByCustomerId(customerId, credits);

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}