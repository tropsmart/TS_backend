package com.softper.ts.servicesImp;

import com.softper.ts.models.Configuration;
import com.softper.ts.models.PaymentMethod;
import com.softper.ts.models.Person;
import com.softper.ts.models.User;
import com.softper.ts.repositories.IConfigurationRepository;
import com.softper.ts.repositories.IPaymentMethodRepository;
import com.softper.ts.repositories.IPersonRepository;
import com.softper.ts.repositories.IUserRepository;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.ConfigurationInput;
import com.softper.ts.resources.inputs.PaymentMethodInput;
import com.softper.ts.resources.outputs.ConfigurationOutput;
import com.softper.ts.services.IConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService implements IConfigurationService {

    @Autowired
    IConfigurationRepository configurationRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IPersonRepository personRepository;

    @Autowired
    IPaymentMethodRepository paymentMethodRepository;





    @Override
    public BaseResponse addPaymentMethod(int userId, PaymentMethodInput paymentMethodInput) {
        BaseResponse response = new BaseResponse();
        try
        {
            User getUser = userRepository.findById(userId).get();
            PaymentMethod newPaymentMethod = new PaymentMethod();
            newPaymentMethod.setAccountNumber(paymentMethodInput.getAccountNumber());
            newPaymentMethod.setBankName(paymentMethodInput.getBankName());
            newPaymentMethod.setBillingAdress(paymentMethodInput.getBillingAdress());
            newPaymentMethod.setSwiftCode(paymentMethodInput.getSwiftCode());
            newPaymentMethod.setConfiguration(getUser.getConfiguration());

            newPaymentMethod = paymentMethodRepository.save(newPaymentMethod);

            ConfigurationOutput getConfigurationOutput = new ConfigurationOutput();
            getConfigurationOutput.setFirstName(getUser.getPerson().getFirstName());
            getConfigurationOutput.setLastName(getUser.getPerson().getLastName());
            getConfigurationOutput.setLanguage(getUser.getConfiguration().getLanguage().toString());
            getConfigurationOutput.setPaymentCurrency(getUser.getConfiguration().getPaymentCurrency().toString());
            getConfigurationOutput.setPhone(getUser.getPerson().getPhone());

            response = new BaseResponse("addPaymentMethod","success",1);
            response.setConfigurationOutput(getConfigurationOutput);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("addPaymentMethod","An error ocurred while getting a configuration"+e.getMessage(),-2);
        }
    }


    @Override
    public BaseResponse findAllConfigurations() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Configuration> configurationList = configurationRepository.findAll();
            List<ConfigurationOutput> configurationOutputList = new ArrayList<>();
            for(Configuration c:configurationList)
            {
                ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
                newConfigurationOutput.setFirstName(c.getUser().getPerson().getFirstName());
                newConfigurationOutput.setLastName(c.getUser().getPerson().getLastName());
                newConfigurationOutput.setLanguage(c.getLanguage().toString());
                newConfigurationOutput.setPhone(c.getUser().getPerson().getPhone());
                newConfigurationOutput.setPaymentCurrency(c.getPaymentCurrency().toString());
                configurationOutputList.add(newConfigurationOutput);
            }
            
            response = new BaseResponse("findAllConfigurations","success",1);
            response.setConfigurationOutputList(configurationOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllConfigurations","An error ocurred while getting a configuration"+e.getMessage(),-2);
        }
    }


    @Override
    public BaseResponse findConfigurationByUserId(int userId) {
        BaseResponse response = new BaseResponse();
        try
        {
            User getUser = userRepository.findById(userId).get();
            ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
            newConfigurationOutput.setFirstName(getUser.getPerson().getFirstName());
            newConfigurationOutput.setLastName(getUser.getPerson().getLastName());
            newConfigurationOutput.setPaymentCurrency(getUser.getConfiguration().getPaymentCurrency().toString());
            newConfigurationOutput.setLanguage(getUser.getConfiguration().getLanguage().toString());
            newConfigurationOutput.setPhone(getUser.getPerson().getPhone());

            response = new BaseResponse("findConfigurationByUserId","success",1);
            response.setConfigurationOutput(newConfigurationOutput);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findConfigurationByUserId","An error ocurred while getting a configuration"+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse updateConfiguration(int userId, ConfigurationInput configurationInput) {
        BaseResponse response = new BaseResponse();
        try
        {
            User getUser = userRepository.findById(userId).get();
            Person getPerson = getUser.getPerson();
            Configuration getConfiguration = getUser.getConfiguration();
            getConfiguration.setLanguage(configurationInput.getLanguage());
            getConfiguration.setPaymentCurrency(configurationInput.getPaymentCurrency());
            getPerson.setFirstName(configurationInput.getFirstName());
            getPerson.setLastName(configurationInput.getLastName());
            getPerson.setPhone(configurationInput.getPhone());
            getPerson = personRepository.save(getPerson);
            getConfiguration = configurationRepository.save(getConfiguration);

            ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
            newConfigurationOutput.setPaymentCurrency(getConfiguration.getPaymentCurrency());
            newConfigurationOutput.setLanguage(getConfiguration.getLanguage());
            newConfigurationOutput.setPhone(getConfiguration.getUser().getPerson().getPhone());
            newConfigurationOutput.setFirstName(getConfiguration.getUser().getPerson().getFirstName());
            newConfigurationOutput.setLastName(getConfiguration.getUser().getPerson().getLastName());

            response = new BaseResponse("updateConfiguration","success",1);
            response.setConfigurationOutput(newConfigurationOutput);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("updateConfiguration","An error ocurred while getting a configuration"+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse generateConfiguration(int userId) {
        BaseResponse response = new BaseResponse();
        try{
            User getUser = userRepository.findById(userId).get();
            if(getUser.getConfiguration()== null)
            {
                Configuration newConfiguration = new Configuration();
                newConfiguration.setLanguage("Spanish");
                newConfiguration.setPaymentCurrency("Soles");

                newConfiguration = configurationRepository.save(newConfiguration);

                getUser.setConfiguration(newConfiguration);
                getUser = userRepository.save(getUser);

                ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
                newConfigurationOutput.setPaymentCurrency(newConfiguration.getPaymentCurrency());
                newConfigurationOutput.setLanguage(newConfiguration.getLanguage());
                newConfigurationOutput.setPhone(newConfiguration.getUser().getPerson().getPhone());
                newConfigurationOutput.setFirstName(newConfiguration.getUser().getPerson().getFirstName());
                newConfigurationOutput.setLastName(newConfiguration.getUser().getPerson().getLastName());
                response = new BaseResponse("generateConfiguration","success",1);
                response.setConfigurationOutput(newConfigurationOutput);
                return response;

            }
            else{
                Configuration getConfiguration = getUser.getConfiguration();
                ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
                newConfigurationOutput.setPaymentCurrency(getConfiguration.getPaymentCurrency());
                newConfigurationOutput.setLanguage(getConfiguration.getLanguage());
                newConfigurationOutput.setPhone(getConfiguration.getUser().getPerson().getPhone());
                newConfigurationOutput.setFirstName(getConfiguration.getUser().getPerson().getFirstName());
                newConfigurationOutput.setLastName(getConfiguration.getUser().getPerson().getLastName());

                response = new BaseResponse("generateConfiguration","success",1);
                response.setConfigurationOutput(newConfigurationOutput);
                return response;
            }
        }catch (Exception e)
        {
            return new BaseResponse("generateConfiguration","An error ocurred while getting a configuration"+e.getMessage(),-2);
        }
    }

    @Override
    public Configuration save(Configuration configuration) throws Exception {
        return configurationRepository.save(configuration);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        configurationRepository.deleteById(id);
    }

    @Override
    public Optional<Configuration> findById(Integer id) throws Exception {
        return configurationRepository.findById(id);
    }

    @Override
    public List<Configuration> findAll() throws Exception {
        return configurationRepository.findAll();
    }
}
