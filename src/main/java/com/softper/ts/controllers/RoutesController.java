package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.servicesImp.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/routes")
public class RoutesController {

    @Autowired
    private RouteService routeService;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllRoutes()
    {
        BaseResponse result = routeService.findAllRoutes();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<BaseResponse> findRouteById(@PathVariable(value="routeId")int routeId)
    {
        BaseResponse result = routeService.findRouteById(routeId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
