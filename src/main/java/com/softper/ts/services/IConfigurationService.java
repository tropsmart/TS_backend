package com.softper.ts.services;

import com.softper.ts.models.Configuration;
import com.softper.ts.resources.comunications.ConfigurationResponse;
import com.softper.ts.resources.inputs.ConfigurationInput;
import com.softper.ts.resources.inputs.PaymentMethodInput;

public interface IConfigurationService extends ICrudService<Configuration> {
    ConfigurationResponse findAllConfigurations();
    ConfigurationResponse findConfigurationByUserId(int userId);
    ConfigurationResponse addPaymentMethod(int userId, PaymentMethodInput paymentMethodInput);
    ConfigurationResponse updateConfiguration(int userId, ConfigurationInput configurationInput);

}
