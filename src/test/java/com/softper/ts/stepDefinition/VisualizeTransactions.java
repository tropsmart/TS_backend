package com.softper.ts.stepDefinition;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.servicesImp.BalanceService;
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
public class VisualizeTransactions {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();

    BaseResponse user;
    UserService userService = new UserService();
    BaseResponse balance;
    BalanceService balanceService = new BalanceService();


    @Given("the user is in their profile window")
    public void the_user_is_in_their_profile_window() {
        user = userService.findUserById(2);
    }

    @When("select the option “See transactions”")
    public void select_the_option_See_transactions() {
        balance = balanceService.findBalanceById(2);
    }

    @Then("the money transaction window will open")
    public void the_money_transaction_window_will_open() {
        if(balance.status == 1)
            System.out.println("VisualizeTransactions scenario 1 test success");
        else
            System.out.println("VisualizeTransaction scenario 1 test failed");
    }


    @When("you select the option See transactions but you not have done one yet")
    public void you_select_the_option_See_transactions_but_you_not_have_done_one_yet() {

        balance = balanceService.findBalanceById(2);
        balance = new BaseResponse("findBalanceById", "You dont have any transaction",1);
    }

    @Then("the money transaction window will open with a message saying You have not made any transactions")
    public void the_money_transaction_window_will_open_with_a_message_saying_You_have_not_made_any_transactions() {
        if(balance.status == 1)
            System.out.println("VisualizeTransactions scenario 1 test success");
        else
            System.out.println("VisualizeTransaction scenario 1 test failed");
    }


    @Then("the window error while viewing transactions, loss of connection to the application.")
    public void the_window_error_while_viewing_transactions_loss_of_connection_to_the_application() {
        System.out.println("Frontend lost connection with the user device");
        balance = new BaseResponse("Conection lost");
    }


    @When("you select the option “see transactions” and you have lost the connection")
    public void you_select_the_option_see_transactions_and_you_have_lost_the_connection() {
        System.out.println("You device lost conection");
    }
}