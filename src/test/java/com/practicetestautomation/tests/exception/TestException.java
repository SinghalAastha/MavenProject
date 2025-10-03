package com.practicetestautomation.tests.exception;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestException {
    private WebDriver driver; //Declaring it in class so that driver can be used within all methods.
    private Logger logger;//logger variable to access in all methods of class

    @BeforeMethod(alwaysRun = true)
    @Parameters("Browser")
    public void setup(String Browser){
        //Initialize logger inside method
        logger=Logger.getLogger(TestException.class.getName());//logger is instance variable of this class
        logger.setLevel(Level.INFO);
        // Open page
        //System.out.println("Running Test in:" + Browser);
        logger.info("Running Test in" + Browser);
        switch (Browser.toLowerCase()){
            case "Chrome":
                driver=new ChromeDriver();
                break;
            case "Firefox":
                driver=new FirefoxDriver();
                break;
            default:
                driver=new ChromeDriver();
                //Warning message using println to use chrome as default browser
                //System.out.println("Configuration for"+ Browser + "is missing So run test in Chrome");
                //Instead user logger.warning
                logger.warning("Configuration for"+ Browser +"is missing So run test in Chrome");
                break;
        }
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        driver.manage().window().maximize();
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void TestLoginFunctionality()
    {
        logger.info("Starting 'TestLoginFunctionality' method execution");
        // Type username student into Username field
        WebElement studentUsername=driver.findElement(By.id("username"));
        logger.info("Type username");
        studentUsername.sendKeys("student");
        // Type password Password123 into Password field
        WebElement studentPassword=driver.findElement(By.id("password"));
        logger.info("Type Password");
        studentPassword.sendKeys("Password123");
        // Push Submit button
        WebElement submitButton=driver.findElement(By.id("submit"));
        logger.info("Click Submit button");
        submitButton.click();
        logger.info("Verify login Functionality");
        // Verify new page URL contains practicetestautomation.com/logged-in-successfully/
        String expectedUrl= "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);//checking exact match
        // Verify new page contains expected text ('Congratulations' or 'successfully logged in')
        String expectedMessage="Congratulations student. You successfully logged in!";
        String pageSource= driver.getPageSource();
        Assert.assertNotNull(pageSource);
        Assert.assertTrue(pageSource.contains(expectedMessage));//check if certain page some text anywhere on page
    }
    @Test
    public void TestIncorrectPasswordText(){
        WebElement usernameRight= driver.findElement(By.id("username"));
        usernameRight.sendKeys("student");
        // Type password incorrectPassword into Password field
        WebElement passwordWrong= driver.findElement(By.id("password"));
        passwordWrong.sendKeys("incorrectPassword");
        // Push Submit button
        WebElement submitClick= driver.findElement(By.className("btn"));
        submitClick.click();
        // Verify error message is displayed
        WebElement message=driver.findElement(By.id("error"));
        Assert.assertTrue(message.isDisplayed());
        // Verify error message text is Your pass'word is invalid!
        String expectedText="Your password is invalid!";
        String actualText=message.getText();
        Assert.assertEquals(actualText,expectedText);
    }
}
