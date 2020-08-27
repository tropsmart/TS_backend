package com.softper.ts.resources.comunications;

import com.softper.ts.resources.outputs.CustomerOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerResponse extends BaseResponse<CustomerOutput>{
    public CustomerResponse(List<CustomerOutput> customerResponseList) {super(customerResponseList);}
    public CustomerResponse(CustomerOutput customerOutput) {super(customerOutput);}
    public CustomerResponse(String message) {super(message);}
}
