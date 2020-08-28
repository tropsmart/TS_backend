package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.SubscriptionResponse;
import com.softper.ts.servicesImp.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionsController {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<SubscriptionResponse> findAllSubscriptions()
    {
        SubscriptionResponse result = subscriptionService.findAllSubscriptions();

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<SubscriptionResponse> findSubscriptionsByUserId(@PathVariable(value = "userId")int userId)
    {
        SubscriptionResponse result = subscriptionService.findSubscriptionsByUserId(userId);

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/plans/{planId}")
    public ResponseEntity<SubscriptionResponse> suscribeDriver(@PathVariable(value = "userId")int userId, @PathVariable(value = "planId")int planId)
    {
        SubscriptionResponse result = subscriptionService.subscribe(userId, planId);

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("{subscriptionId}")
    public ResponseEntity<SubscriptionResponse> cancelSubscription(@PathVariable(value = "subscriptionId")int subscriptionId)
    {
        SubscriptionResponse result = subscriptionService.cancelSubscription(subscriptionId);

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}