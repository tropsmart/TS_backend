package com.softper.ts.services;

import com.softper.ts.models.Price;
import com.softper.ts.resources.comunications.BaseResponse;

public interface IPriceService extends ICrudService<Price> {
    BaseResponse findAllPrices();
    BaseResponse findPriceById(int priceId);
    BaseResponse findPricesByPriceType(int priceType);
}
