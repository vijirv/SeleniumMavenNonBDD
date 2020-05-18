package com.qa.hubspot.tests;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pojo.Credentials;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Properties;

public class LoginPageTest {

    WebDriver driver;
    BasePage basePage;
    Properties prop;
    LoginPage loginPage;
    HomePage homePage;
    Credentials credentials;

    @BeforeTest
    public void setUp(){
        basePage = new BasePage();
        prop = basePage.init_prop();// this prop value passed to the  'init_driver' to access browse name
        driver = basePage.init_driver(prop); //String browserName = prop.getProperty("browser")
        loginPage = new LoginPage(driver);
        credentials = new Credentials(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test(priority = 1)
    public void verifyLoginPageTitleTest(){
        String title = loginPage.getPageTitle();
        System.out.println("Login Page Title is " + title);
        Assert.assertEquals(title,"HubSpot Login");
    }
    @Test(priority = 2)
    public void verifySignUpLinkTest(){
        Assert.assertTrue(loginPage.verifySignUpLink());
    }
    @Test(priority = 3)
    public void loginTest() {
       // loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
       homePage = loginPage.doLogin(credentials);
       String loggedInUserVal = homePage.isUserLoggedIn();
       System.out.println("Logged in user is " + loggedInUserVal);
       Assert.assertEquals(loggedInUserVal,prop.getProperty("accountname"));
    }

   @DataProvider
   public Object[][] getLoginInValidData(){
        Object data [][] = {{"test@gmail.com", "test"},{"test@yahoo.com","test123"},{" ", " "}};
        return data;
    }
    /*
    @Test(priority = 4, dataProvider = "getLoginInValidData", enabled = false)
    public void login_InvalidTestCases(String emailId, String password){
        loginPage.doLogin(emailId, password);

    }
     */

    @AfterTest
    public void tearDown(){
      driver.quit();
    }





}
