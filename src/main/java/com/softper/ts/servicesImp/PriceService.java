package com.softper.ts.servicesImp;

import com.softper.ts.models.Price;
import com.softper.ts.repositories.IPriceRepository;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.outputs.PriceOutput;
import com.softper.ts.services.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService implements IPriceService {

    @Autowired
    IPriceRepository priceRepository;

    @Override
    public Price save(Price price) throws Exception {
        return priceRepository.save(price);
    }

    @Override
    public void deleteById(Integer id) {
        priceRepository.deleteById(id);
    }

    @Override
    public Optional<Price> findById(Integer id) {
        return priceRepository.findById(id);
    }

    @Override
    public List<Price> findAll() throws Exception {
        return priceRepository.findAll();
    }

    @Override
    public BaseResponse findAllPrices() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Price> prices = priceRepository.findAll();
            List<PriceOutput> priceOutputList = new ArrayList<>();
            for (Price p:prices) {
                PriceOutput newPriceOutput = new PriceOutput();
                newPriceOutput.setTotalPrice(p.getTotalPrice());
                newPriceOutput.setTax(p.getTax());
                if(p.getPriceType()==1)
                    newPriceOutput.setPriceFrom("Subscription");
                if(p.getPriceType()==2)
                    newPriceOutput.setPriceFrom("Cargo");
                priceOutputList.add(newPriceOutput);
            }
            response = new BaseResponse("findAllPrices", "success", 1);
            response.setPriceOutputList(priceOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllPrices","An error ocurred while getting the price list : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findPriceById(int priceId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Price getPrice = priceRepository.findById(priceId).get();
            PriceOutput newPriceOutput = new PriceOutput();
            newPriceOutput.setTotalPrice(getPrice.getTotalPrice());
            newPriceOutput.setTax(getPrice.getTax());
            if(getPrice.getPriceType()==1)
                newPriceOutput.setPriceFrom("Subscription");
            if(getPrice.getPriceType()==2)
                newPriceOutput.setPriceFrom("Cargo");

            response = new BaseResponse("findPriceById", "success", 1);
            response.setPriceOutput(newPriceOutput);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findPriceById","An error ocurred while getting the price list : "+e.getMessage(),-2);
        }

    }

    @Override
    public BaseResponse findPricesByPriceType(int priceType) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Price> prices = priceRepository.findPricesByType(priceType);
            List<PriceOutput> priceOutputList = new ArrayList<>();
            for (Price p:prices) {
                PriceOutput newPriceOutput = new PriceOutput();
                newPriceOutput.setTotalPrice(p.getTotalPrice());
                newPriceOutput.setTax(p.getTax());
                if(p.getPriceType()==1)
                    newPriceOutput.setPriceFrom("Subscription");
                if(p.getPriceType()==2)
                    newPriceOutput.setPriceFrom("Cargo");
                priceOutputList.add(newPriceOutput);
            }
            response = new BaseResponse("findPricesByPriceType", "success", 1);
            response.setPriceOutputList(priceOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findPricesByPriceType","An error ocurred while getting the price list : "+e.getMessage(),-2);
        }

    }
}
