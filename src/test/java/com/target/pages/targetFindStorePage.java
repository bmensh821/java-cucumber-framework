package com.target.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class targetFindStorePage extends BasePage {

	private final WebDriver driver;
	private final WebDriverWait wait;

	private static final Logger log = LogManager.getLogger(targetFindStorePage.class);

	// Tune these if needed
	private static final int WAIT_SECONDS = 15;
	private static final int HIGHLIGHT_MS = 700;
	private static final int GAP_MS = 250;
	private static final int SCROLL_PAUSE_MS = 600;
	private static final int STABLE_TRIES = 3;

	private static final By STORE_CARD_BY = By.xpath("//div[@data-test='@store-locator/StoreCard']");

	public targetFindStorePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
	}

	// ---------- Header / page elements ----------
	@FindBy(xpath = "//a[@aria-label='Target home']")
	public WebElement targetHomeLogoLeft;

	@FindBy(xpath = "//input[@data-test='@web/Search/SearchInput']")
	public WebElement searchInputField;

	@FindBy(xpath = "//button[@data-test='@web/Search/SearchButton']")
	public WebElement searchSubmitButton;

	@FindBy(xpath = "//button[@data-test='@web/Search/Microphone']")
	public WebElement searchByVoiceButton;

	@FindBy(xpath = "//a[@id='account-sign-in']")
	public WebElement accountButton;

	@FindBy(xpath = "//a[@data-test='@web/CartLink']")
	public WebElement cartIconLink;

	@FindBy(xpath = "//div[contains(@class,'styles_container__')]/h1")
	public WebElement findAStoreHeader;

	@FindBy(xpath = "//div[contains(@class,'styles_container__')]/div")
	public WebElement nearPlusZipcode;

	@FindBy(xpath = "//a[contains(@class,'styles_storeDirectoryLink__')]")
	public WebElement viewStoresDirectory;

	@FindBy(xpath = "//button[normalize-space(.)='Enter ZIP or city, state']")
	public WebElement enterZipCityOrStateButton;

	@FindBy(xpath = "//button[normalize-space(.)='Filter by services']")
	public WebElement filterByServicesButton;

	// ---------- Public verification ----------
	public void verifyTargetHeaderElementsVisibleWithHighlight() {

		log.info("Validating Target Find a Store page header elements");

		verifyVisibleAndHighlight(targetHomeLogoLeft, "Target Home Logo");
		verifyVisibleAndHighlight(searchInputField, "Search input");
		verifyVisibleAndHighlight(searchSubmitButton, "Search button");
		verifyVisibleAndHighlight(searchByVoiceButton, "Voice search button");
		verifyVisibleAndHighlight(accountButton, "Account button");
		verifyVisibleAndHighlight(cartIconLink, "Cart icon");
		verifyVisibleAndHighlight(findAStoreHeader, "Find a Store header");
		verifyVisibleAndHighlight(nearPlusZipcode, "Near + Zipcode text");
		verifyVisibleAndHighlight(viewStoresDirectory, "View stores directory link");
		verifyVisibleAndHighlight(enterZipCityOrStateButton, "Enter ZIP or city button");
		verifyVisibleAndHighlight(filterByServicesButton, "Filter by services button");

		log.info("Header validation completed successfully");
	}

	public void verifyStoreLocationsSectionDisplayed() {

		log.info("Validating store locations section (ALL store cards)");

		List<WebElement> allCards = loadAllStoreCards();
		Assert.assertTrue("No store cards were found on the Find a Store page.",
				allCards != null && !allCards.isEmpty());

		log.info("Total store cards found: {}", allCards.size());

		int index = 1;
		for (WebElement card : allCards) {
			verifyVisibleAndHighlight(card, "Store card #" + index);
			index++;
		}

		log.info("All store cards validated successfully");
	}

	// ---------- Load all cards (handles lazy-load / infinite scroll) ----------
	private List<WebElement> loadAllStoreCards() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int prevCount = 0;
		int stableTries = 0;

		while (stableTries < STABLE_TRIES) {

			List<WebElement> cards = driver.findElements(STORE_CARD_BY);
			int currentCount = cards.size();

			log.info("Store cards currently loaded: {}", currentCount);

			if (currentCount > prevCount) {
				prevCount = currentCount;
				stableTries = 0; // reset because new cards appeared
			} else {
				stableTries++; // no growth this round
			}

			// Scroll to bottom / last card to trigger lazy load
			if (!cards.isEmpty()) {
				js.executeScript("arguments[0].scrollIntoView({block:'end'});", cards.get(cards.size() - 1));
			} else {
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			}

			sleep(SCROLL_PAUSE_MS);
		}

		return driver.findElements(STORE_CARD_BY);
	}

	// ---------- Helpers ----------
	private void verifyVisibleAndHighlight(WebElement element, String name) {
		log.info("Verifying visibility of: {}", name);

		wait.until(ExpectedConditions.visibilityOf(element));
		highlightThenUnhighlight(element);

		Assert.assertTrue(name + " is not displayed.", element.isDisplayed());
	}

	private void highlightThenUnhighlight(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// scroll to center
			js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

			// save original style
			String originalStyle = String.valueOf(
					js.executeScript("return arguments[0].getAttribute('style') || '';", element)
			);

			// highlight
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1] + " +
							"'; outline: 3px solid red !important; background-color: rgba(255,0,0,0.08) !important;');",
					element, originalStyle
			);

			sleep(HIGHLIGHT_MS);

			// restore
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);

			sleep(GAP_MS);

		} catch (Exception e) {
			log.warn("Highlighting failed: {}", e.getMessage());
		}
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ignored) {
			Thread.currentThread().interrupt();
		}
	}
}
