package com.softper.ts.stepDefinition;

import com.softper.ts.resources.comunications.CustomerResponse;
import com.softper.ts.resources.comunications.DriverResponse;
import com.softper.ts.servicesImp.CustomerService;
import com.softper.ts.servicesImp.DriverService;
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
public class SearchDriverImpl {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();

    DriverResponse driver;
    CustomerResponse customer;
    DriverService driverService = new DriverService();
    CustomerService customerService  = new CustomerService();

    @Given("that the customer is in the search window")
    public void that_the_customer_is_in_the_search_window() {
        customer = customerService.findCustomerById(1);
    }


    @When("enter search information and select the Search option")
    public void enter_search_information_and_select_the_Search_option() {
        driver = driverService.findDriverById(2);
    }

    @Then("drivers are displayed according to search criteria")
    public void drivers_are_displayed_according_to_search_criteria() {
        if(driver != null)
            System.out.println("SearchDriver scenario1 test success");
        else
            System.out.println("SearchDriver scenario1 test failed");
    }

    @When("enter search information and there are no driver according to the search criteria")
    public void enter_search_information_and_there_are_no_driver_according_to_the_search_criteria() {
        // Write code here that turns the phrase above into concrete actions
        driver = driverService.findDriverById(99);
    }

    @Then("no driver found")
    public void no_driver_found() {
        if(driver == null)
            System.out.println("SearchDriver scenario2 test success");
        else
            System.out.println("SearchDriver scenario2 test failed");
    }

}