package com.qa.hubspot.base;

import com.aventstack.extentreports.utils.FileUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public WebDriver driver;
    public Properties prop;

    /**
     * This method is used to initialize the WebDriver on the basis of browserName
     * @param prop
     * @return this method will return driver instance
     */
    public WebDriver init_driver(Properties prop){
        String browserName = null;
        if(System.getProperty("browser") == null){
            browserName = prop.getProperty("browser");
        }else {
            browserName = System.getProperty("browser");
        }
        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("safari")){
            WebDriverManager.getInstance(SafariDriver.class).setup();
            driver = new SafariDriver();
        }
        else{
            System.out.println(browserName + "is not found, please provide the correct browser");
        }

        driver.manage().window().maximize();
        //driver.manage().window().fullscreen(); //-Mac
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
        return driver;
}

    /**
     * Read the properties from config.properties
     * @return
     */
    public Properties init_prop(){
        prop = new Properties();
       try
       { FileInputStream ip = new FileInputStream(".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            System.out.println("Some problem with the config.properties file");
        } catch (IOException e) {
           e.printStackTrace();
       }
       return prop;
    }


    public String getScreenshot(){
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
        File destination = new File(path);

        try {
            FileUtils.copyFile(src,destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;

    }


}
