package com.softper.ts.services;

import com.softper.ts.models.Customer;
import com.softper.ts.resources.comunications.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public interface ICustomerService extends ICrudService<Customer>{
    CustomerResponse findCustomerById(int customerId);
    CustomerResponse getAllCustomers();
    CustomerResponse rechargeCreditsByCustomerId(int customerId, double creditUnits);

}
