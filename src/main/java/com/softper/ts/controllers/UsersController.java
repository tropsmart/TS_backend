package com.softper.ts.controllers;

import com.softper.ts.resources.comunications.BlockedResponse;
import com.softper.ts.resources.comunications.FavoriteResponse;
import com.softper.ts.resources.comunications.UserResponse;
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

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("{userId}/favourites/{userfavouritedId}")
    public ResponseEntity<FavoriteResponse> setUserFavourited(@PathVariable(value = "userId")int userId, @PathVariable(value = "userfavouritedId")int userFavouritedId)
    {
        FavoriteResponse result = userService.setFavourited(userId, userFavouritedId);

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/{userId}/blockeds/{userBlockedId}")
    public ResponseEntity<BlockedResponse> setUserBlocked(@PathVariable(value = "userId")int userId, @PathVariable(value = "userBlockedId")int userBlockedId)
    {
        BlockedResponse result = userService.setBlocked(userId, userBlockedId);

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}/favourites")
    public ResponseEntity<FavoriteResponse> findFavouritesByUserId(@PathVariable(value = "userId")int userId)
    {
        FavoriteResponse result = userService.findFavouritesByUserId(userId);

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}/blockeds")
    public ResponseEntity<BlockedResponse> findBlockedsByUserId(@PathVariable(value = "userId")int userId)
    {
        BlockedResponse result = userService.findBlockedsByUserId(userId);

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/favourites")
    public ResponseEntity<FavoriteResponse> findAllFavourites()
    {
        FavoriteResponse result = userService.findAllFavourites();

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/blockeds")
    public ResponseEntity<BlockedResponse> findAllBlockeds()
    {
        BlockedResponse result = userService.findAllBlockeds();

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
