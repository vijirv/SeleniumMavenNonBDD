package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,AppConstants.DEFAULT_EXPLICIT_TIME_OUT);
    }

    public WebElement getElement(By locator){
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        }
        catch (Exception e){
            System.out.println("Some exception occured while creating the webelement :"+locator);
        }
        return element;
    }

    /**
     *
     * @param locator
     */
    public void doClick(By locator){
        getElement(locator).click();
    }

    public  void doActionsClick(By locator){
        actions.click(getElement(locator)).build().perform();
    }

    public void doSendKeys(By locator, String value){
        getElement(locator).sendKeys(value);
    }

    public void doActionsSendKeys(By locator, String value){
        actions.sendKeys(getElement(locator),value).build().perform();

    }

    public boolean doIsDisplayed(By locator){
        return getElement(locator).isDisplayed();
    }

    public String doGetText(By locator){
        return getElement(locator).getText();
    }

    public void waitForElementPresent(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementVisible(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String doGetPageTitle(String title){
        wait.until(ExpectedConditions.titleIs(title));
        return driver.getTitle();
    }




}
