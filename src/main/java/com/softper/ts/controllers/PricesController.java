package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.servicesImp.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/prices")
public class PricesController {
    @Autowired
    PriceService priceService;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllPrices()
    {
        BaseResponse result = priceService.findAllPrices();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{priceId}")
    public ResponseEntity<BaseResponse> findPriceById(@PathVariable(value = "priceId")int priceId)
    {
        BaseResponse result = priceService.findPriceById(priceId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("price-type/{priceType}")
    public ResponseEntity<BaseResponse> findPricesByPriceType(@PathVariable(value = "priceType")int priceType)
    {
        BaseResponse result =  priceService.findPricesByPriceType(priceType);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
