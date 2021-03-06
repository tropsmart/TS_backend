package com.softper.ts.servicesImp;

import com.softper.ts.models.Plan;
import com.softper.ts.models.Price;
import com.softper.ts.repositories.IPlanRepository;
import com.softper.ts.repositories.IPriceRepository;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.PlanInput;
import com.softper.ts.resources.outputs.PlanOutput;
import com.softper.ts.services.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanService implements IPlanService {

    @Autowired
    private IPlanRepository planRepository;

    @Autowired
    private IPriceRepository priceRepository;


    @Override
    public BaseResponse findPlansByPrice(double priceValue) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Plan> plans = planRepository.findPlansByPriceValue(priceValue);
            List<PlanOutput> planOutputList = new ArrayList<>();
            for (Plan p: plans) {
                PlanOutput newPlanOutput = new PlanOutput();
                newPlanOutput.setId(p.getId());
                newPlanOutput.setDurationDays(p.getDuration());
                newPlanOutput.setPrice(p.getPrice().getTotalPrice());
                newPlanOutput.setTax(p.getPrice().getTax());
                newPlanOutput.setPlanName(p.getName());
                planOutputList.add(newPlanOutput);
            }
            response = new BaseResponse("findPlansByPrice", "success", 1);
            response.setPlanOutputList(planOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findPlansByPrice", "An error ocurred while getting the plan list: "+e.getMessage(),-2);
        }

    }

    @Override
    public BaseResponse findPlansHigherThan(double priceValue) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Plan> plans = planRepository.findPlansHigherThanPriceValue(priceValue);
            List<PlanOutput> planOutputList = new ArrayList<>();
            for (Plan p: plans) {
                PlanOutput newPlanOutput = new PlanOutput();
                newPlanOutput.setId(p.getId());
                newPlanOutput.setDurationDays(p.getDuration());
                newPlanOutput.setPrice(p.getPrice().getTotalPrice());
                newPlanOutput.setTax(p.getPrice().getTax());
                newPlanOutput.setPlanName(p.getName());
                planOutputList.add(newPlanOutput);
            }
            response = new BaseResponse("findPlansHigherThan", "success", 1);
            response.setPlanOutputList(planOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findPlansHigherThan", "An error ocurred while getting the plan list: "+e.getMessage(),-2);
        }

    }

    @Override
    public BaseResponse findPlansLessThan(double priceValue) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Plan> plans = planRepository.findPlansLessThanPriceValue(priceValue);
            List<PlanOutput> planOutputList = new ArrayList<>();
            for (Plan p: plans) {
                PlanOutput newPlanOutput = new PlanOutput();
                newPlanOutput.setId(p.getId());
                newPlanOutput.setDurationDays(p.getDuration());
                newPlanOutput.setPrice(p.getPrice().getTotalPrice());
                newPlanOutput.setTax(p.getPrice().getTax());
                newPlanOutput.setPlanName(p.getName());
                planOutputList.add(newPlanOutput);
            }
            response = new BaseResponse("findPlansLessThan", "success", 1);
            response.setPlanOutputList(planOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findPlansLessThan", "An error ocurred while getting the plan list: "+e.getMessage(),-2);
        }

    }

    @Override
    public BaseResponse findAllPlans() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Plan> plans = planRepository.findAll();
            List<PlanOutput> planOutputList = new ArrayList<>();
            for (Plan p: plans) {
                PlanOutput newPlanOutput = new PlanOutput();
                newPlanOutput.setId(p.getId());
                newPlanOutput.setDurationDays(p.getDuration());
                newPlanOutput.setPrice(p.getPrice().getTotalPrice());
                newPlanOutput.setTax(p.getPrice().getTax());
                newPlanOutput.setPlanName(p.getName());
                planOutputList.add(newPlanOutput);
            }
            response = new BaseResponse("findAllPlans", "success", 1);
            response.setPlanOutputList(planOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllPlans", "An error ocurred while getting the plan list: "+e.getMessage(),-2);
        }

    }

    @Override
    public BaseResponse registerPlan(PlanInput planInput) {
        BaseResponse response = new BaseResponse();
        try
        {
            Price newPrice = new Price();
            newPrice.setTotalPrice(planInput.getPrice());
            newPrice.setTax((planInput.getPrice())*0.19);
            newPrice.setPriceType(1);
            newPrice = priceRepository.save(newPrice);

            Plan newPlan = new Plan();
            newPlan.setName(planInput.getPlanName());
            newPlan.setDuration(planInput.getDurationDays());
            newPlan.setPrice(newPrice);
            newPlan = planRepository.save(newPlan);

            PlanOutput newPlanOutput = new PlanOutput();
            newPlanOutput.setId(newPlan.getId());
            newPlanOutput.setPlanName(newPlan.getName());
            newPlanOutput.setDurationDays(newPlan.getDuration());
            newPlanOutput.setPrice(newPrice.getTotalPrice());
            newPlanOutput.setTax(newPrice.getTax());

            response = new BaseResponse("registerPlan", "success", 1);
            response.setPlanOutput(newPlanOutput);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("registerPlan", "An error ocurred while getting the plan list: "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findPlanById(int planId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Plan getPlan = planRepository.findById(planId).get();
            PlanOutput newPlanOutput = new PlanOutput();
            newPlanOutput.setId(getPlan.getId());
            newPlanOutput.setDurationDays(getPlan.getDuration());
            newPlanOutput.setPrice(getPlan.getPrice().getTotalPrice());
            newPlanOutput.setTax(getPlan.getPrice().getTax());
            newPlanOutput.setPlanName(getPlan.getName());

            response = new BaseResponse("findPlanById", "success", 1);
            response.setPlanOutput(newPlanOutput);
            return response;        
        }
        catch (Exception e)
        {
            return new BaseResponse("findPlanById", "An error ocurred while getting the plan list: "+e.getMessage(),-2);
        }

    }

    @Override
    public BaseResponse deletePlanById(int planId) {
        return null;
    }

    @Override
    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public void deleteById(Integer id) {
        planRepository.deleteById(id);
    }

    @Override
    public Optional<Plan> findById(Integer id) {
        return planRepository.findById(id);
    }

    @Override
    public List<Plan> findAll() throws Exception {
        return planRepository.findAll();
    }
}
