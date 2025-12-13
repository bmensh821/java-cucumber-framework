package com.target.pages;

import com.target.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registry_WishList {

    public Registry_WishList(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//button[@data-test='@site-registry/HomePage/CreateRegistryCards/CreateButton/BABY']")
    public WebElement babyRegistryBtn;



    private By createRegistryButton(String customList) {
        return By.xpath("//button[@data-test='@site-registry/HomePage/CreateRegistryCards/CreateButton/"
                + customList.toUpperCase() + "']");
    }

    public void clickCreateRegistry(String customList) {
        Driver.getDriver().findElement(createRegistryButton(customList)).click();
    }


}
