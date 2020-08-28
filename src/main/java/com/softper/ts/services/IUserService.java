package com.softper.ts.services;

import com.softper.ts.models.User;
import com.softper.ts.resources.comunications.BlockedResponse;
import com.softper.ts.resources.comunications.FavoriteResponse;
import com.softper.ts.resources.comunications.UserResponse;

public interface IUserService extends ICrudService<User>{
    FavoriteResponse setFavourited(int userId, int favoriteId);
    BlockedResponse setBlocked(int userId, int blockedId);
    UserResponse findAllUsers();
    UserResponse findAllUsersDrivers();
    FavoriteResponse findFavoritesByUserId(int userId);
    BlockedResponse findBlockedsByUserId(int userId);
    FavoriteResponse findAllFavourites();
    BlockedResponse findAllBlockeds();
    UserResponse findUserByEmail(String email);
    UserResponse findUserById(int userId);
    FavoriteResponse findFavoriteByUserIdAndFavoriteId(int userId, int favouriteId);
    BlockedResponse findBlockByUserIdAndBlockedId(int userId, int blockedId);
    FavoriteResponse deleteFavoriteByUserIdAndFavoriteId(int userId, int favoriteId);
    BlockedResponse deleteBlockByUserIdAndBlockId(int userId, int blockedId);

}
