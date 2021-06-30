package com.softper.ts.services;

import com.softper.ts.models.Balance;
import com.softper.ts.resources.comunications.BaseResponse;

public interface IBalanceService extends ICrudService<Balance>{
    BaseResponse findBalanceById(int userId);
    BaseResponse findAllBalances();
}
