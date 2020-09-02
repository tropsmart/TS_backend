package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.*;
import com.softper.ts.servicesImp.CustomerService;
import com.softper.ts.servicesImp.DriverService;
import com.softper.ts.servicesImp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> findAllUsers()
    {
        UserResponse result = userService.findAllUsers();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("{userId}/favourites/{userfavoritedId}")
    public ResponseEntity<FavoriteResponse> setUserFavourited(@PathVariable(value = "userId")int userId, @PathVariable(value = "userfavoritedId")int userFavouritedId)
    {
        FavoriteResponse result = userService.setFavourited(userId, userFavouritedId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/{userId}/blockeds/{userBlockedId}")
    public ResponseEntity<BlockedResponse> setUserBlocked(@PathVariable(value = "userId")int userId, @PathVariable(value = "userBlockedId")int userBlockedId)
    {
        BlockedResponse result = userService.setBlocked(userId, userBlockedId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<FavoriteResponse> findFavoritesByUserId(@PathVariable(value = "userId")int userId)
    {
        FavoriteResponse result = userService.findFavoritesByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}/blockeds")
    public ResponseEntity<BlockedResponse> findBlockedsByUserId(@PathVariable(value = "userId")int userId)
    {
        BlockedResponse result = userService.findBlockedsByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/favorites")
    public ResponseEntity<FavoriteResponse> findAllFavourites()
    {
        FavoriteResponse result = userService.findAllFavourites();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/blockeds")
    public ResponseEntity<BlockedResponse> findAllBlockeds()
    {
        BlockedResponse result = userService.findAllBlockeds();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}/customers")
    public ResponseEntity<CustomerResponse> findCustomerByUserId(@PathVariable(value = "userId")int userId)
    {
        CustomerResponse result = userService.findCustomerByUserId(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{userId}/drivers")
    public ResponseEntity<DriverResponse> findDriverByUserId(@PathVariable(value = "userId")int userId)
    {
        DriverResponse result = userService.findDriverByUserId(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
