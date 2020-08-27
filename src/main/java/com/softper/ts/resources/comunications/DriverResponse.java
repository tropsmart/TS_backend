package com.softper.ts.resources.comunications;

import com.softper.ts.resources.outputs.DriverOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DriverResponse extends BaseResponse<DriverOutput> {
    public DriverResponse(List<DriverOutput> driverOutputList) {super(driverOutputList);}
    public DriverResponse(DriverOutput driverOutput) {super(driverOutput);}
    public DriverResponse(String message) {super(message);}
}
