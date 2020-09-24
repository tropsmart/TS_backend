package com.softper.ts.services;

import com.softper.ts.models.Cargo;
import com.softper.ts.resources.comunications.CargoResponse;
import com.softper.ts.resources.comunications.CargoResponseFixed;
import com.softper.ts.resources.inputs.CargoInput;
import org.springframework.stereotype.Service;

@Service
public interface ICargoService extends ICrudService<Cargo>{
    CargoResponse findCargoesByCustomerId(int customerId);
    CargoResponse addCargoByCustomerId(int customerId, CargoInput cargoInput);
    CargoResponse findCargoById(int cargoId);
    CargoResponse findAllCargoes();
    CargoResponseFixed findAllCargoesFixed();
    CargoResponse confirmCargoRequest(int cargoId);
    CargoResponse setCargoDelivered(int cargoId);
    CargoResponse findCargoesByDriverId(int driverId);
    CargoResponse findRequestedCargoesByDriverId(int driverId);
    CargoResponse findConfirmedCargoesByDriverId(int driverId);
    CargoResponse findFinishedCargoesByDriverId(int driverId);
}
