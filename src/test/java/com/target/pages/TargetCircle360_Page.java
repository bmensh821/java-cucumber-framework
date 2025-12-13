package com.target.pages;

import com.target.utilities.BrowserUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


public class TargetCircle360_Page extends BasePage {

	// Represents the Target Circle 360 page and provides WebElement/s, and reusable methods.

	public TargetCircle360_Page(WebDriver driver) {
		super(driver);
	}


	// Locators

	// locates hamburger dropdown and navigates to TargetCircle360
	private final By hamburgerBtn = By.xpath("//a[@aria-label='Main menu']");
	private final By target360NavigationBtn = By.xpath("//span[text()='Target Circle 360™']/ancestor::a");

	// Locator for the "Explore plan options" button.
	private final By planOption = By.xpath("//a[text()='Explore plan options']");

	// Locator for the text message such as "Sign up is simple".
	private final By textMessage = By.xpath("//b[text()='Sign up is simple']");

	// Locator for the "Sign in or create account" button.
	private final By signInOrCreateAccountBtn = By.xpath("//button[text()='Sign in or create account']");

	// Locator for all hyperlinks on the page.
	private final By links = By.tagName("a");

	public void clickTargetCircle360Link() {
		clickWhenVisible(target360NavigationBtn, 15);
	}

	// click on hamburger menu btn
	public void openHamburgerMenu() {
		clickWhenVisible(hamburgerBtn, 15);
	}

	/**
	 * Verifies whether the hamburger menu is currently displayed.
	 *
	 * @return true if the menu container is visible, otherwise false
	 */
	public boolean isMenuDisplayed() {
		return driver.findElement(hamburgerBtn).isDisplayed();
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
