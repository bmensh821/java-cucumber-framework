package com.target.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TargetCircle360 extends BasePage {

	// Represents the Target Circle 360 page and provides WebElement/s, and reusable methods.

	public TargetCircle360(WebDriver driver) {
		super(driver);
	}


		// Dropdown to navigate to the Target 360
    	private By target360Dropdown = By.xpath("//div[@class='styles_burgerMenuContainer__r2C0j']");
		
		// Target360 navigation page button
		private By target360NavigationBtn = By.xpath("//a[@data-tracking='target-circle-360']");
		
		// Locator for the "Explore plan options" button.
		private By planOption = By.xpath("//a[text()='Explore plan options']");

		// Locator for the text message such as "Sign up is simple".
		private By textMessage = By.xpath("//b[text()='Sign up is simple']");

		// Locator for the "Sign in or create account" button.
		private By signInOrCreateAccountBtn = By.xpath("//button[text()='Sign in or create account']");

		// Locator for all hyperlinks on the page.
		private By links = By.tagName("a");



		// Method to click on the dropdown and select Target360 option from main target url
		public void navigateToTarget360() {
			clickWhenVisible(target360Dropdown, 10);        // open menu
			clickWhenVisible(target360NavigationBtn, 10);   // click Target 360 link
		}

		// Method to clicks the "Explore plan options" button.
		public void setPlanOptions() {
			driver.findElement(planOption).click();
		}


		// Retrieves the text of the signup information message "Sign up is simple".
		public String signUpIsSimpleText() {
			return driver.findElement(textMessage).getText();
		}


		// Clicks the button that allows the user to sign in or create a Target account.
		public void signInOrSignUpBtn() {
			driver.findElement(signInOrCreateAccountBtn).click();
		}

		/**
		 * Finds all link elements on the page, prints the total number of links,
		 * prints the text of each non-empty link, and returns the list.
		 */
		public List<WebElement> allLinks() {
			List<WebElement> allLinks = driver.findElements(links);

			System.out.println("Total number of links = " + allLinks.size());

			for (WebElement e : allLinks) {
				String linkText = e.getText();
				if (!linkText.isBlank()) {
					System.out.println(linkText);
				}
			}
			return allLinks;
		}
}
