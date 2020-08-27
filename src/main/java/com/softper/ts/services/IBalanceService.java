package com.softper.ts.services;

import com.softper.ts.models.Balance;
import com.softper.ts.resources.comunications.BalanceResponse;

public interface IBalanceService extends ICrudService<Balance>{
    BalanceResponse findBalanceById(int userId);
    BalanceResponse findAllBalances();
}
