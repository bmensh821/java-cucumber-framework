package com.target;

import com.target.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class WeeklyAdPage {
    public WeeklyAdPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // ===== Weekly Ad Section =====

    @FindBy(xpath = "//h1[contains(text(),'Weekly Ad')]")
    public WebElement weeklyAdTitle;

    @FindBy(xpath = "//button[@role='tab' and contains(text(),' - ')]")
    public WebElement weeklyAdDateRange;

    @FindBy(xpath = "//button[contains(text(),'Weekly Ad Sneak Peek')]")
    public WebElement weeklyAdSneakPeekTab;

    @FindBy(xpath = "//button[contains(text(),'Holiday Kids Catalog')]")
    public WebElement holidayKidsCatalogTab;

    @FindBy(css = "input[placeholder='Search Weekly Ad']")
    public WebElement weeklyAdSearchInput;

    @FindBy(xpath = "//button[@type='submit' and .//span[text()='search']]")
    public WebElement weeklyAdSearchButton;

    @FindBy(xpath = "//button[contains(text(),'Browse By Category')]")
    public WebElement browseByCategoryDropdown;

    @FindBy(xpath = "//div[contains(@class,'ndsPopover')]//a")
    public List<WebElement> weeklyAdCategoryOptions;

    // ===== Weekly Ad page functions =====

    public void clickWeeklyAdDateRangeIfValid() {
        String actualDateRangeText = weeklyAdDateRange.getText().trim();

        DateTimeFormatter uiFormatter =
                DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);

        LocalDate today = LocalDate.now();

        // Start of week (Sunday)
        LocalDate sunday = today.with(DayOfWeek.SUNDAY);

        // End of week (Saturday)
        LocalDate saturday = sunday.plusDays(6);

        String expectedDateRange =
                uiFormatter.format(sunday) + " - " + uiFormatter.format(saturday);

        if (!actualDateRangeText.equalsIgnoreCase(expectedDateRange)) {
            throw new AssertionError(
                    "Weekly Ad date range mismatch. Expected: "
                            + expectedDateRange
                            + " but found: "
                            + actualDateRangeText
            );
        }

        weeklyAdDateRange.click();
    }

    public void searchWeeklyAdItem(String searchText) {
        weeklyAdSearchInput.clear();
        weeklyAdSearchInput.sendKeys(searchText);
        weeklyAdSearchButton.click();
    }

    public void selectWeeklyAdCategory(String categoryName) {

        browseByCategoryDropdown.click();

        for (WebElement option : weeklyAdCategoryOptions) {
            String optionText = option.getText().trim();

            if (optionText.startsWith(categoryName)) {
                option.click();
                return;
            }
        }

        throw new AssertionError(
                "Category not found in Weekly Ad dropdown: " + categoryName
        );
    }

}
