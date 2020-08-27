package com.softper.ts.servicesImp;

import com.softper.ts.exception.ResourceNotFoundException;
import com.softper.ts.models.PaymentMethod;
import com.softper.ts.models.User;
import com.softper.ts.repositories.IPaymentMethodRepository;
import com.softper.ts.repositories.IUserRepository;
import com.softper.ts.resources.comunications.PaymentMethodResponse;
import com.softper.ts.resources.outputs.PaymentMethodOutput;
import com.softper.ts.services.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PaymentMethodService implements IPaymentMethodService {

    @Autowired
    IPaymentMethodRepository paymentMethodRepository;
    @Autowired
    IUserRepository userRepository;

    @Override
    public PaymentMethodResponse findPaymentMethodById(int paymentMethodId) {
        try
        {
            PaymentMethod getPaymentMethod = paymentMethodRepository.findById(paymentMethodId)
                    .orElseThrow(()->new ResourceNotFoundException("paymentMethod","id",paymentMethodId));

            return new PaymentMethodResponse(new PaymentMethodOutput(getPaymentMethod.getBankName(),
                    getPaymentMethod.getSwiftCode(),getPaymentMethod.getAccountNumber(),getPaymentMethod.getDni()));

        }
        catch (Exception e)
        {
            return new PaymentMethodResponse("An error ocurred while getting paymentMethod: "+e.getMessage());
        }
    }

    @Override
    public PaymentMethodResponse findPaymentMethodByUserId(int userId) {
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
            PaymentMethod getPaymentMethod = paymentMethodRepository.findById(getUser.getConfiguration().getPaymentMethod().getId())
                    .orElseThrow(()->new ResourceNotFoundException("Payment Method","Id",userId));

            return new PaymentMethodResponse(new PaymentMethodOutput(getPaymentMethod.getBankName(),
                    getPaymentMethod.getSwiftCode(),getPaymentMethod.getAccountNumber(),getPaymentMethod.getDni()));
        }
        catch (Exception e)
        {
            return new PaymentMethodResponse("An error ocurred while getting paymentMethod: "+e.getMessage());
        }
    }

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) throws Exception {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        paymentMethodRepository.deleteById(id);
    }

    @Override
    public Optional<PaymentMethod> findById(Integer id) throws Exception {
        return paymentMethodRepository.findById(id);
    }

    @Override
    public List<PaymentMethod> findAll() throws Exception {
        return paymentMethodRepository.findAll();
    }
}
