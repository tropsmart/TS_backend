package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.ReviewInput;
import com.softper.ts.services.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    @Autowired
    private IReviewService reviewService;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllReviews()
    {
        BaseResponse result = reviewService.findAllReviews();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<BaseResponse> findReviewsByCustomerId(@PathVariable(value = "customerId")int customerId)
    {
        BaseResponse result = reviewService.findReviewsByCustomerId(customerId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<BaseResponse> findReviewsByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        BaseResponse result = reviewService.findReviewsByDriverId(driverId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<BaseResponse> findReviewById(@PathVariable(value = "reviewId")int reviewId)
    {
        BaseResponse result = reviewService.findReviewById(reviewId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/cargoes/{cargoId}")
    public ResponseEntity<BaseResponse> addReviewByCargoId(@PathVariable(value = "cargoId")int cargoId, @Valid @RequestBody ReviewInput reviewInput)
    {
        BaseResponse result = reviewService.addReviewByCargoId(cargoId, reviewInput);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}