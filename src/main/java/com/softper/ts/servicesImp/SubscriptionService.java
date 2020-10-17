package com.softper.ts.servicesImp;

import com.softper.ts.exception.ResourceNotFoundException;
import com.softper.ts.models.Plan;
import com.softper.ts.models.Subscription;
import com.softper.ts.models.User;
import com.softper.ts.repositories.IPlanRepository;
import com.softper.ts.repositories.ISubscriptionRepository;
import com.softper.ts.repositories.IUserRepository;
import com.softper.ts.resources.comunications.SubscriptionResponse;
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
    public SubscriptionResponse findSubscriptionById(int subscriptionId) {
        try{
            Subscription getSubscription = subscriptionRepository.findById(subscriptionId).get();
            SubscriptionOutput newSubscriptionOutput = new SubscriptionOutput();
            newSubscriptionOutput.setFirstName(getSubscription.getUser().getPerson().getFirstName());
            newSubscriptionOutput.setLastName(getSubscription.getUser().getPerson().getLastName());
            newSubscriptionOutput.setEmail(getSubscription.getUser().getEmail());
            newSubscriptionOutput.setPlan(getSubscription.getPlan().getName());
            newSubscriptionOutput.setId(getSubscription.getId());
            newSubscriptionOutput.setState(getSubscription.getSubscriptionState());
            newSubscriptionOutput.setPrice(getSubscription.getPlan().getPrice().getTotalPrice());
            return new SubscriptionResponse(newSubscriptionOutput);
        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while getting the subscription : "+e.getMessage());
        }

    }

    @Override
    public SubscriptionResponse subscribe(int userId, int planId) {
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

            return new SubscriptionResponse(new SubscriptionOutput(newSubscription.getId(),
                    newSubscription.getUser().getPerson().getFirstName(),newSubscription.getUser().
                    getPerson().getLastName(),newSubscription.getUser().getEmail(),newSubscription.getPlan().
                    getName(),newSubscription.getPlan().getPrice().getTotalPrice(),newSubscription.getSubscriptionState()));

        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while registering the subscription : "+e.getMessage());
        }

    }

    @Override
    public SubscriptionResponse findSubscriptionsByUserId(int userId) {
        try
        {
            List<Subscription> subscriptionList = subscriptionRepository.getSubscriptionsByUserId(userId);
            List<SubscriptionOutput> subscriptionOutputList = new ArrayList<>();
            for (Subscription s:subscriptionList) {
                SubscriptionOutput newSubscriptionOutput = new SubscriptionOutput();
                newSubscriptionOutput.setFirstName(s.getUser().getPerson().getFirstName());
                newSubscriptionOutput.setLastName(s.getUser().getPerson().getLastName());
                newSubscriptionOutput.setEmail(s.getUser().getEmail());
                newSubscriptionOutput.setPlan(s.getPlan().getName());
                newSubscriptionOutput.setId(s.getId());
                newSubscriptionOutput.setState(s.getSubscriptionState());
                newSubscriptionOutput.setPrice(s.getPlan().getPrice().getTotalPrice());
                subscriptionOutputList.add(newSubscriptionOutput);
            }
            return new SubscriptionResponse(subscriptionOutputList);
        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while getting the subscription list : "+e.getMessage());

        }

    }

    @Override
    public SubscriptionResponse findAllSubscriptions() {
        try
        {
            List<Subscription> subscriptionList = subscriptionRepository.findAll();
            List<SubscriptionOutput> subscriptionOutputList = new ArrayList<>();
            for (Subscription s:subscriptionList) {
                SubscriptionOutput newSubscriptionOutput = new SubscriptionOutput();
                newSubscriptionOutput.setFirstName(s.getUser().getPerson().getFirstName());
                newSubscriptionOutput.setLastName(s.getUser().getPerson().getLastName());
                newSubscriptionOutput.setEmail(s.getUser().getEmail());
                newSubscriptionOutput.setPlan(s.getPlan().getName());
                newSubscriptionOutput.setId(s.getId());
                newSubscriptionOutput.setState(s.getSubscriptionState());
                newSubscriptionOutput.setPrice(s.getPlan().getPrice().getTotalPrice());
                subscriptionOutputList.add(newSubscriptionOutput);
            }
            return new SubscriptionResponse(subscriptionOutputList);
        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while getting the subscription list : "+e.getMessage());
        }
    }

    @Override
    public SubscriptionResponse cancelSubscription(int subscriptionId) {
        try
        {
            Subscription getSubscription = subscriptionRepository.findById(subscriptionId)
                    .orElseThrow(()->new ResourceNotFoundException("subscription","id",subscriptionId));
            getSubscription.setSubscriptionState("Canceled");
            getSubscription = subscriptionRepository.save(getSubscription);

            return new SubscriptionResponse(new SubscriptionOutput(getSubscription.getId(),
                    getSubscription.getUser().getPerson().getFirstName(),getSubscription.getUser().
                    getPerson().getLastName(),getSubscription.getUser().getEmail(),getSubscription.getPlan().
                    getName(),getSubscription.getPlan().getPrice().getTotalPrice(),getSubscription.getSubscriptionState()));
        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while getting the subscription : "+e.getMessage());
        }
    }

    @Override
    public SubscriptionResponse enableSubscriptionById(int subscriptionId) {
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

            return new SubscriptionResponse(new SubscriptionOutput(getSubscription.getId(),
                    getSubscription.getUser().getPerson().getFirstName(),getSubscription.getUser().
                    getPerson().getLastName(),getSubscription.getUser().getEmail(),getSubscription.getPlan().
                    getName(),getSubscription.getPlan().getPrice().getTotalPrice(),getSubscription.getSubscriptionState()));

        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while getting the subscription : "+e.getMessage());
        }
    }

    @Override
    public SubscriptionResponse deleteSubscriptionBySubscriptionId(int subscriptionId) {
        try{
            Subscription getSubscription = subscriptionRepository.findById(subscriptionId)
                    .orElseThrow(()-> new ResourceNotFoundException("Id","subscription",subscriptionId));
            subscriptionRepository.deleteById(subscriptionId);
            return new SubscriptionResponse(new SubscriptionOutput(getSubscription.getId(),
                    getSubscription.getUser().getPerson().getFirstName(),getSubscription.getUser().
                    getPerson().getLastName(),getSubscription.getUser().getEmail(),getSubscription.getPlan().
                    getName(),getSubscription.getPlan().getPrice().getTotalPrice(),getSubscription.getSubscriptionState()));
        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while getting the subscription : "+e.getMessage());
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
}
