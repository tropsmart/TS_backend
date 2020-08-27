package com.softper.ts.services;

import com.softper.ts.models.Price;
import com.softper.ts.resources.comunications.PriceResponse;

public interface IPriceService extends ICrudService<Price> {
    PriceResponse findAllPrices();
    PriceResponse findPriceById(int priceId);
    PriceResponse findPricesByPriceType(int priceType);
}
