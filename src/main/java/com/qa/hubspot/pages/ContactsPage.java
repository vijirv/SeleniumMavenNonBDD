package com.qa.hubspot.pages;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pojo.Contacts;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementActions;
import com.qa.hubspot.util.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactsPage extends BasePage {

    WebDriver driver;
    ElementActions elementActions;
    JavaScriptUtil jsUtil;

    By createContactButton = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]");
 //  By createContactFormButton = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]");
  By createContactFormButton = By.xpath("//button[@type='button' and @data-selenium-test='base-dialog-confirm-btn']");

    By email = By.xpath("//input[@data-field='email']");
    By firstName = By.xpath("//input[@data-field='firstname']");
    By lastName = By.xpath("//input[@data-field='lastname']");
    By jobTitle = By.xpath("//input[@data-field='jobtitle']");

   By contactsNavigate = By.xpath("//div[@class='display-flex']//nav[@role='navigation']/a");

    public ContactsPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
        jsUtil = new JavaScriptUtil(driver);
    }

    public String getContactsPageTitle(){
        return elementActions.doGetPageTitle(AppConstants.CONTACTS_PAGE_TITLE);
    }

    /**
     * Using Pojo
     * @param contacts
     */
    public void createContactDataProviderPojo(Contacts contacts){

        elementActions.waitForElementPresent(createContactButton);
        elementActions.doClick(createContactButton);

        elementActions.waitForElementPresent(this.email);
        elementActions.doSendKeys(this.email,contacts.getEmail());

        elementActions.waitForElementPresent(this.firstName);
        elementActions.doSendKeys(this.firstName,contacts.getFirstName());

        elementActions.waitForElementPresent(this.lastName);
        elementActions.doSendKeys(this.lastName,contacts.getLastName());

        elementActions.waitForElementPresent(this.jobTitle);
        elementActions.doSendKeys(this.jobTitle,contacts.getJobTitle());

      //  elementActions.waitForElementPresent(createContactFormButton);
        jsUtil.clickElementByJS(elementActions.getElement(createContactFormButton));

        elementActions.waitForElementPresent(contactsNavigate);
        elementActions.doClick(this.contactsNavigate);
    }

    /**
     * using Data provider
     * @param mail
     * @param FN
     * @param LN
     * @param job
     * @throws InterruptedException
     */
    public void createNewContactsTestDataProvider(String mail,String FN,String LN, String job) throws InterruptedException{

        elementActions.waitForElementPresent(createContactButton);
        elementActions.doClick(createContactButton);

        elementActions.waitForElementPresent(email);
        elementActions.doSendKeys(email,mail);

        elementActions.waitForElementPresent(firstName);
        elementActions.doSendKeys(firstName,FN);

        elementActions.waitForElementPresent(lastName);
        elementActions.doSendKeys(lastName,LN);

        elementActions.waitForElementPresent(jobTitle);
        elementActions.doSendKeys(jobTitle,job);

        jsUtil.clickElementByJS(elementActions.getElement(createContactFormButton));

    //    elementActions.waitForElementPresent(contactsNavigate);
    //    elementActions.doClick(contactsNavigate);
    }

    /**
     * Using single hardcoded values
     * @param mail
     * @param FN
     * @param LN
     * @param job
     * @throws InterruptedException
     */
    public void createNewContactsTest(String mail,String FN,String LN, String job) throws InterruptedException{

        elementActions.waitForElementPresent(createContactButton);
        elementActions.doClick(createContactButton);

        elementActions.waitForElementPresent(email);
        elementActions.doSendKeys(email,mail);

        elementActions.waitForElementPresent(firstName);
        elementActions.doSendKeys(firstName,FN);

        elementActions.waitForElementPresent(lastName);
        elementActions.doSendKeys(lastName,LN);

        elementActions.waitForElementPresent(jobTitle);
        elementActions.doSendKeys(jobTitle,job);

        jsUtil.clickElementByJS(elementActions.getElement(createContactFormButton));

        elementActions.waitForElementPresent(contactsNavigate);
        elementActions.doClick(contactsNavigate);
    }




}
