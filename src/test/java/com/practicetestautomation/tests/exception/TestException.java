package com.practicetestautomation.tests.exception;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestException {
    private WebDriver driver=new ChromeDriver(); //Declaring it in class so that driver can be used within all methods.
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        driver.manage().window().maximize();
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void TestNoSuchElementException(){
        logger.info("Starting 'NoSuchElementException' exception testing");
        //Click Add button
        WebElement addClick= driver.findElement(By.id("add_btn"));
        addClick.click();
        // Verify Row 2 input field is displayed
        WebElement row2=driver.findElement(By.xpath("//div[@id='row2']/input"));
        Assert.assertTrue(row2.isDisplayed(),"Row 2 was added message is not displayed");
    }
}