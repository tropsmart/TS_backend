package com.softper.ts.services;

import com.softper.ts.models.Plan;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.PlanInput;

public interface IPlanService extends ICrudService<Plan>{
    BaseResponse findPlansByPrice(double priceValue);
    BaseResponse findPlansHigherThan(double priceValue);
    BaseResponse findPlansLessThan(double priceValue);
    BaseResponse findAllPlans();
    BaseResponse registerPlan(PlanInput planInput);
    BaseResponse findPlanById(int planId);
    BaseResponse deletePlanById(int planId);
}
