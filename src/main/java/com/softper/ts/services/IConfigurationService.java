package com.softper.ts.services;

import com.softper.ts.models.Configuration;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.ConfigurationInput;
import com.softper.ts.resources.inputs.PaymentMethodInput;

public interface IConfigurationService extends ICrudService<Configuration> {
    BaseResponse findAllConfigurations();
    BaseResponse findConfigurationByUserId(int userId);
    BaseResponse addPaymentMethod(int userId, PaymentMethodInput paymentMethodInput);
    BaseResponse updateConfiguration(int userId, ConfigurationInput configurationInput);
    BaseResponse generateConfiguration(int userId);
}
