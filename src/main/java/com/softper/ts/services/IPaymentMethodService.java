package com.softper.ts.services;

import com.softper.ts.models.PaymentMethod;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.PaymentMethodInput;

public interface IPaymentMethodService extends ICrudService<PaymentMethod> {
    BaseResponse findAllPaymentMethod();
    BaseResponse findPaymentMethodById(int paymentMethodId);
    BaseResponse findPaymentMethodByUserId(int userId);
    BaseResponse addPaymentMethodByUserId(int userId, PaymentMethodInput paymentMethodInput);
}
