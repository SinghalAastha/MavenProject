package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BeforeAfterAnnotation {
    private WebDriver driver; //Declaring it in class so that driver can be used within all methods.
    @BeforeMethod
    public void setup(){
        // Open page
        driver=new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(groups={"Positive","Regression","Smoke"})
    public void TestLoginFunctionality()
    {
        // Type username student into Username field
        WebElement studentUsername=driver.findElement(By.id("username"));
        studentUsername.sendKeys("student");
        // Type password Password123 into Password field
        WebElement studentPassword=driver.findElement(By.id("password"));
        studentPassword.sendKeys("Password123");
        // Push Submit button
        WebElement submitButton=driver.findElement(By.id("submit"));
        submitButton.click();
        // Verify new page URL contains practicetestautomation.com/logged-in-successfully/
        String expectedUrl= "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);//checking exact match
        // Verify new page contains expected text ('Congratulations' or 'successfully logged in')
        String expectedMessage="Congratulations student. You successfully logged in!";
        String pageSource= driver.getPageSource();
        Assert.assertNotNull(pageSource);
        Assert.assertTrue(pageSource.contains(expectedMessage));//check if certain page some text anywhere on page
        // Verify button Log out is displayed on the new page
        WebElement logOutButton=driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logOutButton.isDisplayed());
    }
}
