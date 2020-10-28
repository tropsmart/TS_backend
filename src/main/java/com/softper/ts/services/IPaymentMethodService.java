package com.softper.ts.services;

import com.softper.ts.models.PaymentMethod;
import com.softper.ts.resources.comunications.PaymentMethodResponse;
import com.softper.ts.resources.inputs.PaymentMethodInput;

public interface IPaymentMethodService extends ICrudService<PaymentMethod> {
    PaymentMethodResponse findAllPaymentMethod();
    PaymentMethodResponse findPaymentMethodById(int paymentMethodId);
    PaymentMethodResponse findPaymentMethodByUserId(int userId);
    PaymentMethodResponse addPaymentMethodByUserId(int userId, PaymentMethodInput paymentMethodInput);
}
