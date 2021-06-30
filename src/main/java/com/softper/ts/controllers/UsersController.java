package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BaseResponse;
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
    public ResponseEntity<BaseResponse> findAllUsers()
    {
        BaseResponse result = userService.findAllUsers();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("{userId}/favorites/{userfavoritedId}")
    public ResponseEntity<BaseResponse> setUserFavorited(@PathVariable(value = "userId")int userId, @PathVariable(value = "userfavoritedId")int userFavouritedId)
    {
        BaseResponse result = userService.setFavourited(userId, userFavouritedId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/{userId}/blockeds/{userBlockedId}")
    public ResponseEntity<BaseResponse> setUserBlocked(@PathVariable(value = "userId")int userId, @PathVariable(value = "userBlockedId")int userBlockedId)
    {
        BaseResponse result = userService.setBlocked(userId, userBlockedId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse> findUserById(@PathVariable(value = "userId")int userId)
    {
        BaseResponse result = userService.findUserById(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //========================================================================

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<BaseResponse> findFavoritesByUserId(@PathVariable(value = "userId")int userId)
    {
        BaseResponse result = userService.findFavoritesByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}/blockeds")
    public ResponseEntity<BaseResponse> findBlockedsByUserId(@PathVariable(value = "userId")int userId)
    {
        BaseResponse result = userService.findBlockedsByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/favorites")
    public ResponseEntity<BaseResponse> findAllFavourites()
    {
        BaseResponse result = userService.findAllFavourites();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/blockeds")
    public ResponseEntity<BaseResponse> findAllBlockeds()
    {
        BaseResponse result = userService.findAllBlockeds();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}/customers")
    public ResponseEntity<BaseResponse> findCustomerByUserId(@PathVariable(value = "userId")int userId)
    {
        BaseResponse result = userService.findCustomerByUserId(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{userId}/drivers")
    public ResponseEntity<BaseResponse> findDriverByUserId(@PathVariable(value = "userId")int userId)
    {
        BaseResponse result = userService.findDriverByUserId(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/type/{userType}")
    public ResponseEntity<BaseResponse> findUsersTypeCustomers(@PathVariable(value="userType")int userType)
    {
        BaseResponse result = userService.findAllUsersByType(userType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //====================================================================

    @DeleteMapping("{userId}/favorites/{userFavoritedId}")
    public ResponseEntity<BaseResponse> deleteFavoriteByUserIdAndUserFavoritedId(@PathVariable(value="userId")int userId, @PathVariable(value = "userFavorited")int userFavoritedId)
    {
        BaseResponse result = userService.deleteFavoriteByUserIdAndFavoriteId(userId, userFavoritedId);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @DeleteMapping("{userId}/blockeds/{userBlockedId}")
    public ResponseEntity<BaseResponse> deleteBlockedByUserIdAndBlockedId(@PathVariable(value = "userId")int userId, @PathVariable(value = "userBlockedId")int userBlockedId)
    {
        BaseResponse result = userService.deleteBlockByUserIdAndBlockId(userId, userBlockedId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
