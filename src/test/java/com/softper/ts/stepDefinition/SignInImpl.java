package com.softper.ts.stepDefinition;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.SignIn;
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
public class SignInImpl {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();

    SignIn signIn;

    private AuthService authService = new AuthService();

    private UserService userService = new UserService();

    @Given("the user is in the login window")
    public void the_user_is_in_the_login_window() {
        signIn = new SignIn();
    }

    @When("you enter your email and password correctly and press login")
    public void you_enter_your_email_and_password_correctly_and_press_login() {
        signIn = new SignIn("customer1@gmail.com","password");
    }


    @Then("you access in the application with your account")
    public void you_access_in_the_application_with_your_account() {

        authService.login(signIn.getEmail(),signIn.getPassword());

        BaseResponse getUser = userService.findUserByEmail("customer1@gmail.com");

        if(getUser.status == 1)
            System.out.println("SignUp1 test failed");
        else
            System.out.println("SignUp1 test success");

    }



    @When("you not enter your email and password correctly and press login")
    public void you_not_enter_your_email_and_password_correctly_and_press_login() {
        signIn = new SignIn("customer1@gmail.com","pass");
    }



    @Then("you not access in the application")
    public void you_not_access_in_the_application() {
        authService.login(signIn.getEmail(),signIn.getPassword());

        BaseResponse getUser = userService.findUserByEmail("customer1@gmail.com");

        if(getUser.status != 1)
            System.out.println("SignUp1 test failed");
        else
            System.out.println("SignUp1 test success");

    }


}