package com.softper.ts.services;

import com.softper.ts.models.PaymentMethod;
import com.softper.ts.resources.comunications.PaymentMethodResponse;

public interface IPaymentMethodService extends ICrudService<PaymentMethod> {
    PaymentMethodResponse findPaymentMethodById(int paymentMethodId);
    PaymentMethodResponse findPaymentMethodByUserId(int userId);
}
