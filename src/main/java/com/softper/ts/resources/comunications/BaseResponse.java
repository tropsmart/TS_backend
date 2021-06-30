package com.softper.ts.resources.comunications;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.softper.ts.resources.outputs.AuthenticatedOutput;
import com.softper.ts.resources.outputs.BalanceOutput;
import com.softper.ts.resources.outputs.BlockedOutput;
import com.softper.ts.resources.outputs.CargoOutput;
import com.softper.ts.resources.outputs.ConfigurationOutput;
import com.softper.ts.resources.outputs.CustomerOutput;
import com.softper.ts.resources.outputs.DriverOutput;
import com.softper.ts.resources.outputs.FavoriteOutput;
import com.softper.ts.resources.outputs.PaymentMethodOutput;
import com.softper.ts.resources.outputs.PersonOutput;
import com.softper.ts.resources.outputs.PlanOutput;
import com.softper.ts.resources.outputs.PriceOutput;
import com.softper.ts.resources.outputs.ReviewOutput;
import com.softper.ts.resources.outputs.RouteOutput;
import com.softper.ts.resources.outputs.ServiceOutput;
import com.softper.ts.resources.outputs.SubscriptionOutput;
import com.softper.ts.resources.outputs.UserOutput;
import com.softper.ts.resources.outputs.VehicleOutput;

@Data
public class BaseResponse {
    public String title;
    public String message;
    public Integer status;

    public BaseResponse(String title, String message, Integer status) {
        this.title = title;
        this.message = message;
        this.status = status;
    }

    public BaseResponse(String message)
    {
        this.message = message;
        this.status = 1;
    }

    public BaseResponse() {
        this.status = 1;
    }

    AuthenticatedOutput authenticatedOutput;

    List<AuthenticatedOutput> authenticatedOutputList;

    BalanceOutput balanceOutput;

    List<BalanceOutput> balanceOutputList;

    BlockedOutput blockedOutput;

    List<BlockedOutput> blockedOutputList;

    CargoOutput cargoOutput;

    List<CargoOutput> cargoOutputList;

    ConfigurationOutput configurationOutput;

    List<ConfigurationOutput> configurationOutputList;

    CustomerOutput customerOutput;

    List<CustomerOutput> customerOutputList;

    DriverOutput driverOutput;

    List<DriverOutput> driverOutputList;

    FavoriteOutput favoriteOutput;

    List<FavoriteOutput> favoriteOutputList;

    PaymentMethodOutput paymentMethodOutput;

    List<PaymentMethodOutput> paymentMethodOutputList;

    PersonOutput personOutput;
    
    List<PersonOutput> personOutputList;

    PlanOutput planOutput;

    List<PlanOutput> planOutputList;

    PriceOutput priceOutput;

    List<PriceOutput> priceOutputList;

    ReviewOutput reviewOutput;

    List<ReviewOutput> reviewOutputList;

    RouteOutput routeOutput;

    List<RouteOutput> routeOutputList;

    ServiceOutput serviceOutput;

    List<ServiceOutput> serviceOutputList;

    SubscriptionOutput subscriptionOutput;

    List<SubscriptionOutput> subscriptionOutputList;

    UserOutput userOutput;

    List<UserOutput> userOutputList;

    VehicleOutput vehicleOutput;

    List<VehicleOutput> vehicleOutputList;
    /*
    public BaseResponse(T resource)
    {
        this.resource = resource;
        this.success = true;
        this.message = "Success";
        this.status = 1;
    }

    public BaseResponse(List<T> resource)
    {
        this.resourceList = resource;
        this.success = true;
        this.message = "Success";
        this.status = 1;
    }

   
    */

}
