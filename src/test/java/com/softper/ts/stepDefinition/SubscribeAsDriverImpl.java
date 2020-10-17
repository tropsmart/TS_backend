package com.softper.ts.stepDefinition;

import com.softper.ts.resources.comunications.DriverResponse;
import com.softper.ts.resources.comunications.SubscriptionResponse;
import com.softper.ts.servicesImp.DriverService;
import com.softper.ts.servicesImp.SubscriptionService;
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
public class SubscribeAsDriverImpl {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();

    DriverResponse driver;
    DriverService driverService = new DriverService();
    SubscriptionService subscriptionService = new SubscriptionService();


    @Given("the driver is in the subscription window")
    public void the_driver_is_in_the_subscription_window() {
        driver = driverService.findDriverById(1);
    }

    @When("enter all the registration data and select the “Subscribe” option")
    public void enter_all_the_registration_data_and_select_the_Subscribe_option() {
        subscriptionService.subscribe(1,2);
    }

    @Then("the driver suscribe to the service")
    public void the_driver_suscribe_to_the_service() {
        SubscriptionResponse subscription = subscriptionService.findSubscriptionById(1);
        if(subscription!=null)
            System.out.println("SuscribeAsDriver test success");
        else
            System.out.println("SuscribeAsDriver test failed");
    }



    @When("select the Suscribe option and the registration data is incomplete")
    public void select_the_Suscribe_option_and_the_registration_data_is_incomplete() {
        // Write code here that turns the phrase above into concrete actions
        subscriptionService.subscribe(1,9999);
    }


    @Then("the driver does not subscribe to the service")
    public void the_driver_does_not_subscribe_to_the_service() {

        SubscriptionResponse subscription = subscriptionService.findSubscriptionById(1);
        if(subscription==null)
            System.out.println("SuscribeAsDriver test success");
        else
            System.out.println("SuscribeAsDriver test failed");
    }


}
