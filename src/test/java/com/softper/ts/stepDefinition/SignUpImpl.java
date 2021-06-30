package com.softper.ts.stepDefinition;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.SignUp;
import com.softper.ts.servicesImp.AuthService;
import com.softper.ts.servicesImp.UserService;
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
public class SignUpImpl {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();

    SignUp signUp;

    private AuthService authService = new AuthService();

    private UserService userService = new UserService();


    @Given("the user is in the register window")
    public void the_user_is_in_the_register_window() {
        signUp = new SignUp();
    }


    @When("you enter your credentials correctly")
    public void you_enter_your_credentials_correctly() {
        // Write code here that turns the phrase above into concrete actions
        signUp = new SignUp("Jhon", "Doe", "customer1@gmail.com", "password", "922345189", 2);
    }

    @Then("you are registered in the application")
    public void you_are_registered_in_the_application() {

        authService.registerComplete(signUp);

        BaseResponse getUser = userService.findUserByEmail("customer1@gmail.com");

        if(getUser.status == 1)
            System.out.println("SignUp1 test failed");
        else
            System.out.println("SignUp1 test success");

    }


    @When("you not enter your credentials correctly")
    public void you_not_enter_your_credentials_correctly() {
        signUp = new SignUp("Jhon", "Doe", "", "password", "", 2);

    }

    @Then("you are not registered in the application")
    public void you_are_not_registered_in_the_application() {
        // Write code here that turns the phrase above into concrete actions
        authService.registerComplete(signUp);

        BaseResponse getUser = userService.findUserByEmail("customer1@gmail.com");

        if(getUser.status != 1)
            System.out.println("SignUp2 test failed");
        else
            System.out.println("SignUp2 test success");

    }


}