package com.softper.ts.servicesImp;

import com.softper.ts.exception.ResourceNotFoundException;
import com.softper.ts.models.Plan;
import com.softper.ts.models.Subscription;
import com.softper.ts.models.User;
import com.softper.ts.repositories.IPlanRepository;
import com.softper.ts.repositories.ISubscriptionRepository;
import com.softper.ts.repositories.IUserRepository;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.outputs.BlockedOutput;
import com.softper.ts.resources.outputs.SubscriptionOutput;
import com.softper.ts.services.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService implements ISubscriptionService {
    @Autowired
    private ISubscriptionRepository subscriptionRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPlanRepository planRepository;

    @Override
    public BaseResponse findSubscriptionById(int subscriptionId) {
        BaseResponse response = new BaseResponse();
        try{
            Subscription getSubscription = subscriptionRepository.findById(subscriptionId).get();
            response = new BaseResponse("findSubscriptionById","success",1);
            response.setSubscriptionOutput(toSubscriptionOutput(getSubscription));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findSubscriptionById", "An error ocurred while getting the subscription : "+e.getMessage(), -2);
        }

    }

    @Override
    public BaseResponse subscribe(int userId, int planId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Plan getPlan = planRepository.findById(planId).get();
            User getUser = userRepository.findById(userId).get();

            Subscription newSubscription = new Subscription();
            newSubscription.setPlan(getPlan);
            newSubscription.setUser(getUser);
            newSubscription.setSubscriptionState("Actived");
            newSubscription.setStartTime(Calendar.getInstance().getTime());
            newSubscription.setFinishTime(Calendar.getInstance().getTime());

            List<Subscription> subscriptionList = subscriptionRepository.getSubscriptionsByUserId(userId);

            for (Subscription s: subscriptionList) {
                s.setSubscriptionState("Disabled");
            }

            subscriptionRepository.saveAll(subscriptionList);
            newSubscription = subscriptionRepository.save(newSubscription);

            response = new BaseResponse("subscribe","success",1);
            response.setSubscriptionOutput(toSubscriptionOutput(newSubscription));
            return response;

        }
        catch (Exception e)
        {
            return new BaseResponse("subscribe", "An error ocurred while getting the subscription : "+e.getMessage(), -2);
        }

    }

    @Override
    public BaseResponse findSubscriptionsByUserId(int userId) {
        BaseResponse response = new BaseResponse();
        try
        {
            User getUser = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
            if(getUser.getPerson().getPersonType() == 1)
                return new BaseResponse("findSubscriptionsByUserId", "Subscriptions solo están disponibles para los conductores", 0);
            List<Subscription> subscriptionList = subscriptionRepository.getSubscriptionsByUserId(userId);
            System.out.print("subscription list : "+ subscriptionList.size());
            List<SubscriptionOutput> subscriptionOutputList = new ArrayList<>();
            for (Subscription s:subscriptionList) {
                subscriptionOutputList.add(toSubscriptionOutput(s));
            }

            response = new BaseResponse("findSubscriptionsByUserId", "success",1);
            response.setSubscriptionOutputList(subscriptionOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findSubscriptionsByUserId", "An error ocurred while getting the subscription list : "+e.getMessage(), -2);

        }

    }

    @Override
    public BaseResponse findAllSubscriptions() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Subscription> subscriptionList = subscriptionRepository.findAll();
            List<SubscriptionOutput> subscriptionOutputList = new ArrayList<>();
            for (Subscription s:subscriptionList) {
                subscriptionOutputList.add(toSubscriptionOutput(s));
            }
            response = new BaseResponse("findAllSubscriptions", "success",1);
            response.setSubscriptionOutputList(subscriptionOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllSubscriptions", "An error ocurred while getting the subscription list : "+e.getMessage(), -2);
        }
    }

    @Override
    public BaseResponse cancelSubscription(int subscriptionId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Subscription getSubscription = subscriptionRepository.findById(subscriptionId)
                    .orElseThrow(()->new ResourceNotFoundException("subscription","id",subscriptionId));
            getSubscription.setSubscriptionState("Canceled");
            getSubscription = subscriptionRepository.save(getSubscription);

            response = new BaseResponse("cancelSubscription","success",1);
            response.setSubscriptionOutput(toSubscriptionOutput(getSubscription));
            return response;        
        }
        catch (Exception e)
        {
            return new BaseResponse("cancelSubscription", "An error ocurred while getting the subscription list : "+e.getMessage(), -2);
        }
    }

    @Override
    public BaseResponse enableSubscriptionById(int subscriptionId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Subscription getSubscription = subscriptionRepository.findById(subscriptionId)
                    .orElseThrow(()->new ResourceNotFoundException("Id","subscription", subscriptionId));

            List<Subscription> subscriptionList = subscriptionRepository.getSubscriptionsByUserId(getSubscription.getUser().getId());
            for (Subscription s: subscriptionList) {
                if(s.getId()!=subscriptionId)
                    s.setSubscriptionState("Disabled");
                else
                    s.setSubscriptionState("Actived");
                s = subscriptionRepository.save(s);
            }

            response = new BaseResponse("enableSubscriptionById","success",1);
            response.setSubscriptionOutput(toSubscriptionOutput(getSubscription));
            return response;      

        }
        catch (Exception e)
        {
            return new BaseResponse("enableSubscriptionById", "An error ocurred while getting the subscription list : "+e.getMessage(), -2);
        }
    }

    @Override
    public BaseResponse deleteSubscriptionBySubscriptionId(int subscriptionId) {
        BaseResponse response = new BaseResponse();
        try{
            Subscription getSubscription = subscriptionRepository.findById(subscriptionId)
                    .orElseThrow(()-> new ResourceNotFoundException("Id","subscription",subscriptionId));
            subscriptionRepository.deleteById(subscriptionId);
            response = new BaseResponse("deleteSubscriptionBySubscriptionId","success",1);
            response.setSubscriptionOutput(toSubscriptionOutput(getSubscription));
            return response;              
        }
        catch (Exception e)
        {
            return new BaseResponse("deleteSubscriptionBySubscriptionId", "An error ocurred while getting the subscription list : "+e.getMessage(), -2);
        }
    }

    @Override
    public Subscription save(Subscription subscription) throws Exception {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public Optional<Subscription> findById(Integer id) throws Exception {
        return subscriptionRepository.findById(id);
    }

    @Override
    public List<Subscription> findAll() throws Exception {
        return subscriptionRepository.findAll();
    }

    public SubscriptionOutput toSubscriptionOutput (Subscription subscription) {
        SubscriptionOutput newSubscriptionOutput = new SubscriptionOutput();
        newSubscriptionOutput.setFirstName(subscription.getUser().getPerson().getFirstName());
        newSubscriptionOutput.setLastName(subscription.getUser().getPerson().getLastName());
        newSubscriptionOutput.setEmail(subscription.getUser().getEmail());
        newSubscriptionOutput.setPlan(subscription.getPlan().getName());
        newSubscriptionOutput.setId(subscription.getId());
        newSubscriptionOutput.setState(subscription.getSubscriptionState());
        newSubscriptionOutput.setPrice(subscription.getPlan().getPrice().getTotalPrice());
        return newSubscriptionOutput;
    }
}
