package com.target.step_definitions;

import com.target.pages.TargetCircle360_Page;
import com.target.utilities.ConfigurationReader;
import com.target.utilities.Driver;
import io.cucumber.java.en.*;

public class TargetCircle360_stepDefinition {

	TargetCircle360_Page circlePage = new TargetCircle360_Page(Driver.getDriver());

	@Given("I open the Target homepage")
	public void openTargetHomepage() {
		String baseUrl = ConfigurationReader.getProperty("url");
		Driver.getDriver().get(baseUrl);
	}

	@When("I click the hamburger menu")
	public void clickHamburgerMenu() {
		circlePage.openHamburgerMenu();
	}

	@And("I click the Target Circle 360 link")
	public void clickTargetCircle360Link() {
		circlePage.clickTargetCircle360Link();
	}
	@Then("I should be on the Target Circle 360 page")
	public void verifyTargetCircle360Page() {
		String currentUrl = Driver.getDriver().getCurrentUrl();
		assert currentUrl.contains("target-circle-360/-/N-2rguk");
	}

}
