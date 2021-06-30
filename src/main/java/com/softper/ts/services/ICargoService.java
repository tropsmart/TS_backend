package com.softper.ts.services;

import com.softper.ts.models.Cargo;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.CargoInput;
import org.springframework.stereotype.Service;

@Service
public interface ICargoService extends ICrudService<Cargo>{
    BaseResponse findCargoesByCustomerId(int customerId);
    BaseResponse addCargoByCustomerId(int customerId, CargoInput cargoInput);
    BaseResponse findCargoById(int cargoId);
    BaseResponse findAllCargoes();
    BaseResponse findAllCargoesFixed();
    BaseResponse confirmCargoRequest(int cargoId);
    BaseResponse setCargoDelivered(int cargoId);
    BaseResponse rejectCargoById(int cargoId);
    BaseResponse findCargoesByDriverId(int driverId);
    BaseResponse findRequestedCargoesByDriverId(int driverId);
    BaseResponse findConfirmedCargoesByDriverId(int driverId);
    BaseResponse findFinishedCargoesByDriverId(int driverId);
}
