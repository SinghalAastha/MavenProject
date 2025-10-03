package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerLevels {
    WebDriver driver= new ChromeDriver();
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    public void setup()
    {
        logger=Logger.getLogger(LoggerLevels.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Use logger to hit the website");
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        logger.info("Quit the window");
        driver.quit();
    }
    @Test(alwaysRun = true)
    public void testLogger(){
        logger.info("Start Logger Level Test execution");
        WebElement usernameRight= driver.findElement(By.id("username"));
        logger.info("Enter the username" +usernameRight);
        usernameRight.sendKeys("student");
        // Type password incorrectPassword into Password field
        WebElement password= driver.findElement(By.id("password"));
        logger.info("Enter the password" +password);
        password.sendKeys("Password123");
        // Push Submit button
        WebElement submitClick= driver.findElement(By.className("btn"));
        // Verify error message is displayed
        //As we are giving correct credentials the error message wont display
        //We will get error on console and test case will fail, try same using breakpoint.
        WebElement message=driver.findElement(By.id("error"));
        Assert.assertTrue(message.isDisplayed());
    }
}

