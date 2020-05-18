package com.qa.hubspot.tests;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pojo.Contacts;
import com.qa.hubspot.pojo.Credentials;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ExcelUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Properties;

public class ContactsPageTest {
    Properties prop;
    WebDriver driver;
    BasePage basePage;
    LoginPage loginPage;
    HomePage homePage;
    ContactsPage contactsPage;
    Contacts contacts;
    Credentials credentials;

    @BeforeMethod
    public void setUp(){
        basePage = new BasePage();
        prop = basePage.init_prop();
        driver = basePage.init_driver(prop);
        loginPage = new LoginPage(driver);
        credentials = new Credentials(prop.getProperty("username"),prop.getProperty("password"));
        homePage = loginPage.doLogin(credentials);
        contactsPage = homePage.goToContactsPage();
    }

    @Test(priority = 1)
    public void verifyContactPageTitleTest(){
        String title= contactsPage.getContactsPageTitle();
        System.out.println("Contact page title is "+ title);
        Assert.assertEquals(title, AppConstants.CONTACTS_PAGE_TITLE);
    }

    @DataProvider
    public  Object[][] getContactsData(){
        Object [][] data = ExcelUtil.getTestData(AppConstants.CONTACTS_SHEET_NAME);
        return data;
    }
   @Test(priority = 2,dataProvider = "getContactsData")
   public void createContactsDPTest(String email,String firstName,String lastName, String jobTitle) throws InterruptedException {
       contactsPage.createNewContactsTestDataProvider(email,firstName,lastName,jobTitle);
   }

    @Test(priority = 3)
    public void createContactsTest() throws InterruptedException{
      System.out.println("contact test");
      contactsPage.createNewContactsTest("Vijitest13@yahoo.com","VijiTest","RamTest","Developer");
   }

    @AfterMethod
    public  void tearDown(){
        driver.quit();
    }





}
