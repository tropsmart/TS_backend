package com.softper.ts.controllers;

import com.softper.ts.models.PaymentMethod;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.PaymentMethodInput;
import com.softper.ts.servicesImp.PaymentMethodService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllPaymentMethods()
    {
        BaseResponse result = paymentMethodService.findAllPaymentMethod();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<BaseResponse> findPaymentMethodsByUser(@PathVariable(value = "userId")int userId)
    {
        BaseResponse result = paymentMethodService.findPaymentMethodByUserId(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("users/{userId}")
    public ResponseEntity<BaseResponse> addPaymentMethod(@PathVariable(value = "userId")int userId, @Valid @RequestBody PaymentMethodInput paymentMethodInput)
    {
        BaseResponse result = paymentMethodService.addPaymentMethodByUserId(userId, paymentMethodInput);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
