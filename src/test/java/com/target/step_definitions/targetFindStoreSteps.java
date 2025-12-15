package com.target.step_definitions;

import com.target.pages.targetFindStorePage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class targetFindStoreSteps {

    private final WebDriver driver;
    private final targetFindStorePage findStorePage;

    public targetFindStoreSteps() {
        this.driver = com.target.utilities.Driver.getDriver(); // <-- update to your driver provider
        this.findStorePage = new targetFindStorePage(driver);
        PageFactory.initElements(driver, this.findStorePage);
    }

    @Given("I open the Target home page")
    public void iOpenTheTargetHomePage() {
        driver.get("https://www.target.com/");
    }

    @When("I navigate to the Target Find a Store page")
    public void iNavigateToTheTargetFindAStorePage() {
        // Direct navigation is stable and avoids header/menu A/B differences
        driver.get("https://www.target.com/store-locator/find-stores");
    }

    @Then("the Find a Store page should be displayed")
    public void theFindAStorePageShouldBeDisplayed() {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(
                "Expected Find a Store page URL but got: " + url,
                url.contains("/store-locator") || url.contains("find-stores")
        );
    }

    @Then("the top header menu should be visible on Find a Store page")
    public void theTopHeaderMenuShouldBeVisibleOnFindAStorePage() {
        findStorePage.verifyTargetHeaderElementsVisibleWithHighlight();
    }

    @Then("store locations section should be displayed on Find a Store page")
    public void storeLocationsSectionShouldBeDisplayedOnFindAStorePage() {
        findStorePage.verifyStoreLocationsSectionDisplayed();
    }
}
