package com.softper.ts.services;

import com.softper.ts.models.Customer;
import com.softper.ts.resources.comunications.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface ICustomerService extends ICrudService<Customer>{
    BaseResponse findCustomerById(int customerId);
    BaseResponse findAllCustomers();
    BaseResponse rechargeCreditsByCustomerId(int customerId, double creditUnits);

}
