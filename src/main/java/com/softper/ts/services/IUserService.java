package com.softper.ts.services;

import com.softper.ts.models.User;
import com.softper.ts.resources.comunications.BlockedResponse;
import com.softper.ts.resources.comunications.FavoriteResponse;
import com.softper.ts.resources.comunications.UserResponse;

public interface IUserService extends ICrudService<User>{
    FavoriteResponse setFavourited(int userId, int favouriteId);
    BlockedResponse setBlocked(int userId, int blockedId);
    UserResponse findAllUsers();
    UserResponse findAllUsersDrivers();
    FavoriteResponse findFavouritesByUserId(int userId);
    BlockedResponse findBlockedsByUserId(int userId);
    FavoriteResponse findAllFavourites();
    BlockedResponse findAllBlockeds();
    FavoriteResponse findFavouriteByUserIdAndFavouriteId(int userId, int favouriteId);
    BlockedResponse findBlockByUserIdAndBlockedId(int userId, int blockedId);
    UserResponse findByEmail(String email);
    UserResponse findUserById(int userId);
    FavoriteResponse findFavouritesByActiveUser(int userId);
    FavoriteResponse findFavouritesByPassiveUser(int userId);
    BlockedResponse findBlockedsByActiveUser(int userId);
    BlockedResponse findBlockedsByPassiveUser(int userId);
}
