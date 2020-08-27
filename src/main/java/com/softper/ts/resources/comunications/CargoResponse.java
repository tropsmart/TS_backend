package com.softper.ts.resources.comunications;

import com.softper.ts.resources.outputs.CargoOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CargoResponse extends BaseResponse<CargoOutput>{
    public CargoResponse(List<CargoOutput> cargoOutputList) {super(cargoOutputList);}
    public CargoResponse(CargoOutput cargoOutput) {super(cargoOutput);}
    public CargoResponse(String message) { super(message);}
}
