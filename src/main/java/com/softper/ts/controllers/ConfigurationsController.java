package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.ConfigurationInput;
import com.softper.ts.resources.inputs.PaymentMethodInput;
import com.softper.ts.servicesImp.ConfigurationService;
import com.softper.ts.servicesImp.PaymentMethodService;
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

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllConfigurations()
    {
        BaseResponse result = configurationService.findAllConfigurations();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<BaseResponse> findConfigurationByUserId(@PathVariable(value = "userId") int userId)
    {
        BaseResponse result = configurationService.findConfigurationByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/payment-method")
    public ResponseEntity<BaseResponse> findPaymentMethodsByUserId(@PathVariable(value = "userId")int userId)
    {
        BaseResponse result = paymentMethodService.findPaymentMethodByUserId(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/payment-method")
    public ResponseEntity<BaseResponse> addPaymentMethod(@PathVariable(value="userId")int userId, @Valid @RequestBody PaymentMethodInput paymentMethodInput)
    {
        BaseResponse result = configurationService.addPaymentMethod(userId, paymentMethodInput);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<BaseResponse> updateConfiguration(@PathVariable(value="userId")int userId, @Valid @RequestBody ConfigurationInput configurationInput)
    {
        BaseResponse result = configurationService.updateConfiguration(userId, configurationInput);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}