package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.ConfigurationResponse;
import com.softper.ts.resources.inputs.ConfigurationInput;
import com.softper.ts.resources.inputs.PaymentMethodInput;
import com.softper.ts.servicesImp.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("api/configurations")
public class ConfigurationsController {

    @Autowired
    private ConfigurationService configurationService;

    @GetMapping
    public ResponseEntity<ConfigurationResponse> findAllConfigurations()
    {
        ConfigurationResponse result = configurationService.findAllConfigurations();

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<ConfigurationResponse> findConfigurationByUserId(@PathVariable(value = "userId") int userId)
    {
        ConfigurationResponse result = configurationService.findConfigurationByUserId(userId);

        if(!result.success)
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/payment-method")
    public ResponseEntity<ConfigurationResponse> addPaymentMethod(@PathVariable(value="userId")int userId, @Valid @RequestBody PaymentMethodInput paymentMethodInput)
    {
        ConfigurationResponse result = configurationService.addPaymentMethod(userId, paymentMethodInput);

        if(!result.success)
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<ConfigurationResponse> updateConfiguration(@PathVariable(value="userId")int userId, @Valid @RequestBody ConfigurationInput configurationInput)
    {
        ConfigurationResponse result = configurationService.updateConfiguration(userId, configurationInput);

        if(!result.success)
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}