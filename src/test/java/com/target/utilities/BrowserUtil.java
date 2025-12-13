package com.target.utilities;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class BrowserUtil {
	public static void switchWindowAndVerify(String expectedInUrl, String expectedTitle) {

		Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();

		for (String eachWindow : allWindowHandles) {
			Driver.getDriver().switchTo().window(eachWindow);

			if (Driver.getDriver().getCurrentUrl().contains(expectedInUrl)) {
				break;
			}
		}

		String actualTitle = Driver.getDriver().getTitle();

		Assert.assertEquals("Title verification failed", actualTitle.contains(expectedTitle));

	}

	public static void verifyTitle(WebDriver driver, String expectedTitle) {

		Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);

	}

	public static void waitForInvisibilityOf(WebElement element) {

		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public static void verifyURLContains(String expectedInURL) {
		Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(expectedInURL));
	}

	/*
    this method will accept a dropdown as a WebElement
    and return all the options' text in a list of String.
    @parm dropdownElement
    @return List<String> actualOptionsAsString
     */
	public static List<String> dropDownOptionsAsString(WebElement dropdownElement) {
		Select select = new Select(dropdownElement);

		// List of all ACTUAL months <options> as a web element
		List<WebElement> actualOptionsAsWebElement = select.getOptions();

		// List of all ACTUAL months <options> as a string
		List<String> actualOptionsAsString = new ArrayList<>();

		for (WebElement each : actualOptionsAsWebElement) {
			actualOptionsAsString.add(each.getText());
		}
		return actualOptionsAsString;
	}

    /*
    This method will accept a group radio buttons as a list of what WebElement.
    It will loop through the List, and click to the radio button with provided attributesValue
    @param radioButtons
    @param attributeValue
     */

	public static void clickRadioButton(List<WebElement> radioButtons, String attributeValue) {
		for (WebElement each : radioButtons) {
			if (each.getAttribute("value").equalsIgnoreCase(attributeValue)) {
				each.click();
			}
		}
	}

	/*
    Switches to new window by exact title. Returns to original window if target title not found
    @param targetTitle
     */
	public static void switchToWindow(String targetTitle) {
		String origin = Driver.getDriver().getWindowHandle();
		for (String handle : Driver.getDriver().getWindowHandles()) {
			Driver.getDriver().switchTo().window(handle);
			if (Driver.getDriver().getTitle().equals(targetTitle)) {
				return;
			}
		}
		Driver.getDriver().switchTo().window(origin);
	}

	/*
    More than mouse to given element
    @param element on which to hover
     */
	public static void hover(WebElement element) {
		Actions actions = new Actions(Driver.getDriver());
		actions.moveToElement(element).perform();
	}

	/*
    Return a list of string from a list of elements
    @param List of webElements
    @return list of string
     */
	public static List<String> getElementText(List<WebElement> list) {
		List<String> elementTexts = new ArrayList<>();
		for (WebElement el : list) {
			elementTexts.add(el.getText());
		}
		return elementTexts;
	}

	/*
    Extracts text from list of elements matching the provided locator into new List <string>
    @param locator
    @return list of strings
     */
	public static List<String> getElementsText(By locator) {

		List<WebElement> elems = Driver.getDriver().findElements(locator);
		List<String> elemTexts = new ArrayList<>();
		for (WebElement el : elems) {
			elemTexts.add(el.getText());
		}
		return elemTexts;
	}

	/*
    performs a pause
    @param seconds
     */
	public static void waitFor(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
    Wait for the provided element to be visible on the page
    @param element
    @param timeToWaitInSec
    @return
     */
	public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/*
    Wait for element matching the locator to be visible on the page
    @param locators
    @param timeout
    @return
     *
    public static WebElement waitForVisibility(By locator, int timeout){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /*
    Wait for provided element to be clickable
    @param element
    @param timeout
    @return
     */
	public static void waitForClickability(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

    /*
    Wait for  element matching the locator to be clickable
    @param locator
    @param timeOut
    @returnF
     */


	/**
	 * Waits until the element located by the given locator
	 * becomes visible on the page.
	 *
	 * @param locator element locator
	 * @param timeout maximum wait time in seconds
	 * @return visible WebElement
	 */
	public static WebElement waitForVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Waits until the element located by the given locator
	 * becomes clickable.
	 *
	 * @param locator element locator
	 * @param timeout maximum wait time in seconds
	 * @return clickable WebElement
	 */
	public static WebElement waitForClickability(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	/**
	 * Waits until the element located by the given locator
	 * is present in the DOM (not necessarily visible).
	 *
	 * @param locator element locator
	 * @param timeout maximum wait time in seconds
	 * @return located WebElement
	 */
	public static WebElement waitForPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * Waits until the specified attribute of an element
	 * contains the expected value.
	 * Commonly used for animated components (e.g., hamburger menus).
	 *
	 * @param locator element locator
	 * @param attribute attribute name
	 * @param value expected attribute value
	 * @param timeout maximum wait time in seconds
	 */
	public static void waitForAttribute(By locator, String attribute, String value, int timeout) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
	}

}

