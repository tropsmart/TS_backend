package com.softper.ts.services;

import com.softper.ts.models.User;
import com.softper.ts.resources.comunications.BaseResponse;

public interface IUserService extends ICrudService<User>{
    BaseResponse setFavourited(int userId, int favoriteId);
    BaseResponse setBlocked(int userId, int blockedId);
    BaseResponse findAllUsers();
    BaseResponse findAllUsersByType(int userType);
    BaseResponse findFavoritesByUserId(int userId);
    BaseResponse findBlockedsByUserId(int userId);
    BaseResponse findAllFavourites();
    BaseResponse findAllBlockeds();
    BaseResponse findUserByEmail(String email);
    BaseResponse findUserById(int userId);
    BaseResponse findFavoriteByUserIdAndFavoriteId(int userId, int favouriteId);
    BaseResponse findBlockByUserIdAndBlockedId(int userId, int blockedId);
    BaseResponse deleteFavoriteByUserIdAndFavoriteId(int userId, int favoriteId);
    BaseResponse deleteBlockByUserIdAndBlockId(int userId, int blockedId);

    BaseResponse findCustomerByUserId(int userId);
    BaseResponse findDriverByUserId(int userId);

}
