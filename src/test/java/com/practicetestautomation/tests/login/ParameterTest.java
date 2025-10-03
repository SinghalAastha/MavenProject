package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//
public class ParameterTest {
    // Step 1:Add Parameter annotation and the parameters in double quotes should match the parameters on .xml file
    @Parameters({"username","password","expectedMessage"})
    @Test(groups={"Negative","Regression"})
    //Step 2: Add the Parameters in the method parameters
    public static void negativeLoginTest(String username, String password, String expectedMessage) {
        WebDriver driver= new ChromeDriver();
        // Open page
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        // Type username incorrectUser into Username field
        WebElement usernameWrong= driver.findElement(By.id("username"));
        //Step 3: Replace the double quotes key values with the method parameters. Ex: "student" is replaced from .sendKeys to username parameter.
        usernameWrong.sendKeys(username);
        // Type password Password123 into Password field
        WebElement passwordRight= driver.findElement(By.id("password"));
        passwordRight.sendKeys(password);
        // Push Submit button
        WebElement submitClick= driver.findElement(By.className("btn"));
        submitClick.click();
        // Verify error message is displayed
        WebElement message=driver.findElement(By.id("error"));
        // Assert.assertTrue(message.isDisplayed());
        // System.out.println(message);
        // Verify error message text is Your username is invalid!

        //Step 4: Comment out the expectedMessage variable from the method body as it is declared in method parameter
        //String expectedErrorMessage="Your username is invalid!";

        //Way 1:
        String actualMessage= driver.getPageSource();
        //Step 5:Use the parameter expectedMessage as required
        Assert.assertTrue(actualMessage.contains(expectedMessage));
        //Way 2:
        //String actual=message.getText();
        //Assert.assertEquals(actual,expectedMessage);
        driver.quit();
    }
    @Test(groups={"Negative","Regression"})
    public static void TestIncorrectPasswordText(){
        WebDriver driver= new ChromeDriver();
        // Open page
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        // Type username student into Username field
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
        // Verify error message text is Your password is invalid!
        String expectedText="Your password is invalid!";
        String actualText=message.getText();
        Assert.assertEquals(actualText,expectedText);
        driver.quit();
    }
}
