package com.qa.hubspot.pages;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    WebDriver driver;
    ElementActions elementActions;

    By homePageHeader = By.cssSelector("h1.private-page__title");
    By loggedInUserName = By.cssSelector("span.account-name ");
    By parentContacts = By.id("nav-primary-contacts-branch");
    By childContacts =By.id("nav-secondary-contacts");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }

    public String getHomePageTitle(){
        return elementActions.doGetPageTitle(AppConstants.HOME_PAGE_TITLE);
    }

    public String isHomePageTitleExists(){
        elementActions.waitForElementPresent(homePageHeader);
        if(elementActions.doIsDisplayed(homePageHeader)){
            return elementActions.doGetText(homePageHeader);
        }
        return  null;
    }

    public String isUserLoggedIn(){
        elementActions.waitForElementPresent(loggedInUserName);
        if(elementActions.doIsDisplayed(loggedInUserName)){
            return elementActions.doGetText(loggedInUserName);
        }
        return null;
    }

    public ContactsPage goToContactsPage(){
        clickOnContacts();
        return new ContactsPage(driver);
    }

    private void clickOnContacts(){
        elementActions.waitForElementPresent(parentContacts);
        elementActions.doClick(parentContacts);
        elementActions.waitForElementPresent(childContacts);
        elementActions.doClick(childContacts);
    }



}
