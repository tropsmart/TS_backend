package com.softper.ts.resources.comunications;

import com.softper.ts.resources.outputs.PlanOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlanResponse extends BaseResponse<PlanOutput>{
    public PlanResponse(List<PlanOutput> planOutputList) {super(planOutputList);}
    public PlanResponse(PlanOutput planOutput) {super(planOutput);}
    public PlanResponse(String message) { super(message);}
}
