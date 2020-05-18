package com.qa.hubspot.pages;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pojo.Credentials;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    WebDriver driver;
    ElementActions elementActions;

    //locators - By
    By emailId = By.id("username");
    By password = By.id("password");
    By login =By.id("loginBtn");
    By signUpLink = By.linkText("Sign up");
    By errorMsg = By.cssSelector("h5.private-alert__title");

    //Constructor of page class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }

    //Page actions/methods

    public String getPageTitle(){
        return elementActions.doGetPageTitle(AppConstants.LOGIN_PAGE_TITLE);
    }

    public boolean verifySignUpLink(){
       //return driver.findElement(SignUpLink).isDisplayed();
        elementActions.waitForElementPresent(signUpLink);
        return elementActions.doIsDisplayed(signUpLink);
    }

    public HomePage doLogin(Credentials credentials){
        System.out.println("Credentials are " + credentials.getEmailId() + "" +credentials.getPassword());
        elementActions.doSendKeys(emailId,credentials.getEmailId());
        elementActions.doSendKeys(password,credentials.getPassword());
        elementActions.doClick(login);
        return new HomePage(driver);
    }

    public boolean checkLoginErrorMessage(){
        return elementActions.doIsDisplayed(errorMsg);
    }




}
