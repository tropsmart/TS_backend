package com.softper.ts.stepDefinition;

import com.softper.ts.resources.comunications.FavoriteResponse;
import com.softper.ts.resources.comunications.UserResponse;
import com.softper.ts.servicesImp.UserService;
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
public class AddFavoriteDriver {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();

    UserResponse user;
    UserResponse favourite;
    UserService userService = new UserService();
    FavoriteResponse favouriteResponse = new FavoriteResponse();

    @When("you select the “Add to Favorites” option")
    public void you_select_the_Add_to_Favorites_option() {
        user = userService.findUserById(1);
        favourite = userService.findUserById(3);
        userService.setFavourited(1,3);
    }

    @Then("the carrier is added to the customer's favorites list and the message “Added to favorites” is displayed")
    public void the_carrier_is_added_to_the_customer_s_favorites_list_and_the_message_Added_to_favorites_is_displayed() {
        favouriteResponse = userService.findFavoriteByUserIdAndFavoriteId(1,3);
        if(favouriteResponse.success)
            System.out.println("AddFavouriteDriver scenario1 test success");
        else
            System.out.println("AddFavouriteDriver scenario1 test failed");
    }

    @When("you don't select the “Add to Favorites” option")
    public void you_don_t_select_the_Add_to_Favorites_option() {
        System.out.println("In frontend not select favourites option");
    }

    @Then("the carrier is not added to the customer's favorites list,")
    public void the_carrier_is_not_added_to_the_customer_s_favorites_list() {
        favouriteResponse = userService.findFavoriteByUserIdAndFavoriteId(1,3);
        if(favouriteResponse.success)
            System.out.println("AddFavouriteDriver scenario1 test success");
        else
            System.out.println("AddFavouriteDriver scenario1 test failed");
    }
}