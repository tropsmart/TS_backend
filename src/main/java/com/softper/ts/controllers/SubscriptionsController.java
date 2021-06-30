package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BaseResponse;
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
    public ResponseEntity<BaseResponse> findAllSubscriptions()
    {
        BaseResponse result = subscriptionService.findAllSubscriptions();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<BaseResponse> findSubscriptionsByUserId(@PathVariable(value = "userId")int userId)
    {
        BaseResponse result = subscriptionService.findSubscriptionsByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    @PostMapping("/users/{userId}/plans/{planId}")
    public ResponseEntity<BaseResponse> subscribeDriver(@PathVariable(value = "userId")int userId, @PathVariable(value = "planId")int planId)
    {
        BaseResponse result = subscriptionService.subscribe(userId, planId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("{subscriptionId}/disable")
    public ResponseEntity<BaseResponse> cancelSubscription(@PathVariable(value = "subscriptionId")int subscriptionId)
    {
        BaseResponse result = subscriptionService.cancelSubscription(subscriptionId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{subscriptionId}/enable")
    public ResponseEntity<BaseResponse> enableSubscription(@PathVariable(value = "subscriptionId")int subscriptionId)
    {
        BaseResponse result = subscriptionService.enableSubscriptionById(subscriptionId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{subscriptionId}")
    public ResponseEntity<BaseResponse> deleteSubscriptionBySubscriptionId(@PathVariable(value = "subscriptionId")int subscriptionId)
    {
        BaseResponse result = subscriptionService.deleteSubscriptionBySubscriptionId(subscriptionId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}