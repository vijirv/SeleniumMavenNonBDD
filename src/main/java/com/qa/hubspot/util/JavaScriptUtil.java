package com.qa.hubspot.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    WebDriver driver;

    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElementByJS(WebElement element){
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].click();",element);
    }

    public void checkPageIsReady(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        if(js.executeScript("return document.readyState").toString().equals("complete")){
            System.out.println("Page is loaded");
            return;
        }
        for(int i =0; i<25; i++){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(js.executeScript("return document.readyState").toString().equals("complete")){
                break;
            }

        }
    }



}
