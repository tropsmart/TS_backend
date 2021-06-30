package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.PlanInput;
import com.softper.ts.servicesImp.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/plans")
public class PlansController {

    @Autowired
    private PlanService planService;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllPlans()
    {
        BaseResponse result =  planService.findAllPlans();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> addNewPlan(@Valid @RequestBody PlanInput planInput)
    {
        BaseResponse result = planService.registerPlan(planInput);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<BaseResponse> findPlanById(@PathVariable(value = "planId") int planId)
    {
        BaseResponse result = planService.findPlanById(planId);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{priceValue}/more")
    public ResponseEntity<BaseResponse> findPlansHigherThan(@PathVariable(value = "priceValue")double priceValue)
    {
        BaseResponse result =  planService.findPlansHigherThan(priceValue);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{priceValue}/less")
    public ResponseEntity<BaseResponse> findPlansLessThan(@PathVariable(value = "priceValue")double priceValue)
    {
        BaseResponse result = planService.findPlansLessThan(priceValue);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{priceValue}")
    public ResponseEntity<BaseResponse> findByPrice(@PathVariable(value = "priceValue")double priceValue)
    {
        BaseResponse result = planService.findPlansByPrice(priceValue);

        return new ResponseEntity<>(result,HttpStatus.OK);

    }
}
