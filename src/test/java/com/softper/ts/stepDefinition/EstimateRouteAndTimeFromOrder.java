package com.softper.ts.stepDefinition;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.outputs.RouteOutput;
import com.softper.ts.servicesImp.CargoService;
import com.softper.ts.servicesImp.DriverService;
import com.softper.ts.servicesImp.RouteService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EstimateRouteAndTimeFromOrder {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();

    BaseResponse driver;
    BaseResponse cargo;
    BaseResponse route;
    DriverService driverService = new DriverService();
    CargoService cargoService = new CargoService();
    RouteService routeService = new RouteService();

    @Given("that the driver is in the details window of the selected cargo service")
    public void that_the_driver_is_in_the_details_window_of_the_selected_cargo_service() {
        driver = driverService.findDriverById(1);
        cargo = cargoService.findCargoById(2);
    }

    @When("you have the GPS activated and select the option See estimated route")
    public void you_have_the_GPS_activated_and_select_the_option_See_estimated_route() {
        route = routeService.findRouteById(1);
        routeService.getRouteInfo(2);
        RouteOutput routeOutput = route.getRouteOutput();

    }

    @Then("a map opens showing the estimated route of the order address")
    public void a_map_opens_showing_the_estimated_route_of_the_order_address() {
        BaseResponse response = new BaseResponse("Deploying maps");
        System.out.println(response.getMessage());
        System.out.println("Sent the info o location to frontend and display the location with Google Maps API");
    }

    @When("you have the GPS deactivated and select the option See estimated route")
    public void you_have_the_GPS_deactivated_and_select_the_option_See_estimated_route() {
        System.out.println("Driver GPS its desactivated, so the feature cannot be initialized");
    }

    @Then("the message Activate GPS to continue is displayed")
    public void the_message_Activate_GPS_to_continue_is_displayed() {
        BaseResponse response = new BaseResponse("getRouteOutput","Activate your GPS to check your current locatión",1);
        System.out.println(response.getMessage());
        System.out.println("A message is returned 'Activate your GPS to check your current locatión'");
    }
}
