package com.target.step_definitions;

import com.target.pages.Registry_WishList;
import com.target.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Registry_WishList_stepDefs {

    Registry_WishList target = new Registry_WishList();
    @Given("user is on Registry & WishList page")
    public void user_is_on_registry_wish_list_page() {
        Driver.getDriver().get("https://www.target.com/gift-registry");
    }
    @When("user clicks on {string} button")
    public void user_clicks_on_button(String custom) {
        target.clickCreateRegistry(custom);
        //BrowserUtil.sleep(3);

    }
    @Then("user redirected to {string} page")
    public void user_redirected_to_page(String custom) {


    }

}
