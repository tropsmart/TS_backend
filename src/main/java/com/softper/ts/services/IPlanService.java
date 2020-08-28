package com.softper.ts.services;

import com.softper.ts.models.Plan;
import com.softper.ts.resources.comunications.PlanResponse;
import com.softper.ts.resources.inputs.PlanInput;

public interface IPlanService extends ICrudService<Plan>{
    PlanResponse findPlansByPrice(double priceValue);
    PlanResponse findPlansHigherThan(double priceValue);
    PlanResponse findPlansLessThan(double priceValue);
    PlanResponse findAllPlans();
    PlanResponse registerPlan(PlanInput planInput);
    PlanResponse findPlanById(int planId);
    PlanResponse deletePlanById(int planId);
}
