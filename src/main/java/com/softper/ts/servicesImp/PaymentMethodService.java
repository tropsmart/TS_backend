package com.softper.ts.servicesImp;

import com.softper.ts.exception.ResourceNotFoundException;
import com.softper.ts.models.PaymentMethod;
import com.softper.ts.models.User;
import com.softper.ts.repositories.IPaymentMethodRepository;
import com.softper.ts.repositories.IUserRepository;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.PaymentMethodInput;
import com.softper.ts.resources.outputs.PaymentMethodOutput;
import com.softper.ts.services.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService implements IPaymentMethodService {

    @Autowired
    IPaymentMethodRepository paymentMethodRepository;
    @Autowired
    IUserRepository userRepository;

    @Override
    public BaseResponse findAllPaymentMethod() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();
            List<PaymentMethodOutput> paymentMethodOutputList = new ArrayList<>();
            for (PaymentMethod p:paymentMethodList) {
                paymentMethodOutputList.add(new PaymentMethodOutput(p.getBankName(),
                        p.getSwiftCode(),p.getAccountNumber()));
            }
            response = new BaseResponse("findAllPaymentMethod","success",1);
            response.setPaymentMethodOutputList(paymentMethodOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllPaymentMethod","An error ocurred while getting paymentMethod: "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findPaymentMethodById(int paymentMethodId) {
        BaseResponse response = new BaseResponse();
        try
        {
            PaymentMethod getPaymentMethod = paymentMethodRepository.findById(paymentMethodId)
                    .orElseThrow(()->new ResourceNotFoundException("paymentMethod","id",paymentMethodId));
            response = new BaseResponse("findPaymentMethodById","success",1);

            response.setPaymentMethodOutput(new PaymentMethodOutput(getPaymentMethod.getBankName(),
                    getPaymentMethod.getSwiftCode(),getPaymentMethod.getAccountNumber()));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findPaymentMethodById","An error ocurred while getting paymentMethod: "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findPaymentMethodByUserId(int userId) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<PaymentMethod> paymentMethodList = paymentMethodRepository.findPaymentMethodByUserId(userId);
            List<PaymentMethodOutput> paymentMethodOutputList = new ArrayList<>();
            for (PaymentMethod p:paymentMethodList) {
                paymentMethodOutputList.add(new PaymentMethodOutput(p.getBankName(),
                        p.getSwiftCode(),p.getAccountNumber()));
            }

            response = new BaseResponse("findPaymentMethodByUserId","success",1);
            response.setPaymentMethodOutputList(paymentMethodOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findPaymentMethodByUserId","An error ocurred while getting paymentMethod: "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse addPaymentMethodByUserId(int userId, PaymentMethodInput paymentMethodInput) {
        BaseResponse response = new BaseResponse();
        try{
            User getUser = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("id","user",userId));
            PaymentMethod newPaymentMethod = new PaymentMethod();
            newPaymentMethod.setAccountNumber(paymentMethodInput.getAccountNumber());
            newPaymentMethod.setBankName(paymentMethodInput.getBankName());
            newPaymentMethod.setBillingAdress(paymentMethodInput.getBillingAdress());
            newPaymentMethod.setSwiftCode(paymentMethodInput.getSwiftCode());
            newPaymentMethod.setConfiguration(getUser.getConfiguration());

            newPaymentMethod = paymentMethodRepository.save(newPaymentMethod);

            response = new BaseResponse("addPaymentMethodByUserId","success",1);
            response.setPaymentMethodOutput(toPaymentMethodOutput(newPaymentMethod));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("addPaymentMethodByUserId","An error ocurred while getting paymentMethod: "+e.getMessage(),-2);
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

    public PaymentMethodOutput toPaymentMethodOutput(PaymentMethod paymentMethod)
    {
        return new PaymentMethodOutput(paymentMethod.getBankName(),
            paymentMethod.getSwiftCode(),paymentMethod.getAccountNumber());
    }
}
