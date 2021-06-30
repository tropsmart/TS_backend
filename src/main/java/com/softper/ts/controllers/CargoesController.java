package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.CargoInput;
import com.softper.ts.services.ICargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/cargoes")
public class CargoesController {

    @Autowired
    private ICargoService cargoService;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllCargoes()
    {
        BaseResponse result = cargoService.findAllCargoes();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/fixed")
    public ResponseEntity<BaseResponse> findAllCargoesFixed()
    {
        BaseResponse result = cargoService.findAllCargoesFixed();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        //
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{cargoId}")
    public ResponseEntity<BaseResponse> findCargoById(@PathVariable(value = "cargoId")int cargoId)
    {
        BaseResponse result = cargoService.findCargoById(cargoId);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("customers/{customerId}")
    public ResponseEntity<BaseResponse> findCargoesByCustomerId(@PathVariable(value="customerId")int customerId)
    {
        BaseResponse result = cargoService.findCargoesByCustomerId(customerId);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("drivers/{driverId}")
    public ResponseEntity<BaseResponse> findCargoesByDriverId(@PathVariable(value="driverId")int driverId)
    {
        BaseResponse result = cargoService.findCargoesByDriverId(driverId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{cargoId}/confirms")
    public ResponseEntity<BaseResponse> setCargoConfirmed(@PathVariable(value = "cargoId")int cargoId)
    {
        BaseResponse result = cargoService.confirmCargoRequest(cargoId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{cargoId}/deliver")
    public ResponseEntity<BaseResponse> setCargoDelivered(@PathVariable(value="cargoId")int cargoId)
    {
        BaseResponse result = cargoService.setCargoDelivered(cargoId);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{cargoId}/reject")
    public ResponseEntity<BaseResponse> setCargoRejected(@PathVariable(value = "cargoId")int cargoId)
    {
        BaseResponse result = cargoService.rejectCargoById(cargoId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/customers/{customerId}")
    public ResponseEntity<BaseResponse> postCargo(@PathVariable(value = "customerId")int customerId, @RequestBody CargoInput cargoInput)
    {
        BaseResponse result = cargoService.addCargoByCustomerId(customerId,cargoInput);
        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
