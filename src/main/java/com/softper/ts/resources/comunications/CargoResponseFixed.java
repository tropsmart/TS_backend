package com.softper.ts.resources.comunications;

import com.softper.ts.resources.outputs.CargoOutputFixed;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CargoResponseFixed extends BaseResponse<CargoOutputFixed>{
    public CargoResponseFixed(List<CargoOutputFixed> cargoOutputList) {super(cargoOutputList);}
    public CargoResponseFixed(CargoOutputFixed cargoOutput) {super(cargoOutput);}
    public CargoResponseFixed(String message) { super(message);}
}
