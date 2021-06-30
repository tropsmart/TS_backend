package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.services.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/balances")
public class BalanceController {

    @Autowired
    private IBalanceService balanceService;

    @GetMapping()
    public ResponseEntity<BaseResponse> findAllBalances()
    {
        BaseResponse result = balanceService.findAllBalances();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{balanceId}")
    public ResponseEntity<BaseResponse> findBalanceById(@PathVariable(value = "balanceId")int balanceId)
    {
        BaseResponse result =  balanceService.findBalanceById(balanceId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}