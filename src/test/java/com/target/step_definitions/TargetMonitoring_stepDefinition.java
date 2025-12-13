package com.target.step_definitions;

import com.target.pages.BasePage;
import com.target.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.List;

public class TargetMonitoring_stepDefinition extends BasePage {

	public TargetMonitoring_stepDefinition() {
		super(Driver.getDriver());
	}

	public TargetMonitoring_stepDefinition(WebDriver driver) {
		super(driver);
	}

    @When("I inspect the page scripts")
    public void inspectPageScripts() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("script")));
        List<WebElement> scripts = driver.findElements(By.tagName("script"));
        assertFalse("No scripts found!", scripts.isEmpty());
    }

    @Then("I should see a script with id {string}")
    public void verifyScriptById(String scriptId) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("script")));
        WebElement script = driver.findElement(By.id(scriptId));
        assertNotNull("Script with id " + scriptId + " not found!", script);
    }

	@Then("the script should contain {string}")
	public void verifyScriptContains(String expectedText) {

		String pageSource = driver.getPageSource();
		assertTrue("Expected text not found: " + expectedText,
				pageSource.contains(expectedText));
	}
}
