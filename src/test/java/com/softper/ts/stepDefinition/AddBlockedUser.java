package com.softper.ts.stepDefinition;

import com.softper.ts.resources.comunications.BaseResponse;
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
public class AddBlockedUser {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();

    BaseResponse user;
    BaseResponse blocked;
    UserService userService = new UserService();
    BaseResponse baseResponse = new BaseResponse();

    @Given("that the user is in the profile window of the selected other user")
    public void that_the_user_is_in_the_profile_window_of_the_selected_other_user() {
        user = userService.findUserById(1);
        blocked = userService.findUserById(3);
        userService.setFavourited(1,3);
    }

    @When("you select the “Block user” option")
    public void you_select_the_Block_user_option() {
        baseResponse = userService.findBlockByUserIdAndBlockedId(1,3);
    }

    @Then("the user is added to the User's blocked list and the message “User blocked” is displayed")
    public void the_user_is_added_to_the_User_s_blocked_list_and_the_message_User_blocked_is_displayed() {

        if(baseResponse.getStatus() == 1)
            System.out.println("AddBlockedUser scenario1 test success");
        else
            System.out.println("AddBlockedUser scenario1 test failed");
    }

    @When("you don't select the “Block user” option")
    public void you_don_t_select_the_Block_user_option() {
        System.out.println("Int the frontend dont select block user");
    }

    @Then("the user is not added to the user's blockeds list")
    public void the_user_is_not_added_to_the_user_s_blockeds_list() {
        if(baseResponse.getStatus() != 1)
            System.out.println("AddBlockedUser scenario2 test success");
        else
            System.out.println("AddBlockedUser scenario2 test failed");
    }
}