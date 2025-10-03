package com.practicetestautomation.tests.login;

//import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    @Test(groups={"Positive","Regression","Smoke"})
    public void TestLoginFunctionality()
    {
        // Open page
        WebDriver driver=new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        // Type username student into Username field
        WebElement studentUsername=driver.findElement(By.id("username"));
        studentUsername.sendKeys("student");
        // Type password Password123 into Password field
        WebElement studentPassword=driver.findElement(By.id("password"));
        studentPassword.sendKeys("Password123");
        // Push Submit button
        WebElement submitButton=driver.findElement(By.id("submit"));
        submitButton.click();
        /* Use thread.sleep to wait sometime for action to perform
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

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
        driver.quit();
    }
    @Test(groups={"Negative","Regression"})
    public static void UseEdgeDriver(){
        //Check for current Microsoft Edge version "Version 140.0.3485.81 (Official build) (64-bit)"
        //Go to Selenium.dev/downloads/ and download Edge 'Stable Channel x64'
        //Extract the .zip file
        //Copy 'msedgedriver.exe' and paste on resources in IntelliJ.
        //Copy its 'Path from content Root' and paste in System.setProperty
        System.setProperty("WebDriver.edge.driver","src/main/resources/msedgedriver.exe");
        WebDriver driver= new EdgeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        driver.quit();
    }
    @Test(groups={"Negative","Regression","Smoke"})
    public static void TestIncorrectUsernameText() {
        WebDriver driver= new ChromeDriver();
        // Open page
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        // Type username incorrectUser into Username field
        WebElement usernameWrong= driver.findElement(By.id("username"));
        usernameWrong.sendKeys("incorrectUser");
        // Type password Password123 into Password field
        WebElement passwordRight= driver.findElement(By.id("password"));
        passwordRight.sendKeys("Password123");
        // Push Submit button
        WebElement submitClick= driver.findElement(By.className("btn"));
        submitClick.click();
        // Verify error message is displayed
        WebElement message=driver.findElement(By.id("error"));
        // Assert.assertTrue(message.isDisplayed());
        // System.out.println(message);
        // Verify error message text is Your username is invalid!
        String expectedMessage="Your username is invalid!";
        //Way 1:
        String actualMessage= driver.getPageSource();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
        //Way 2:
        //String actual=message.getText();
        //Assert.assertEquals(actual,expectedMessage);
        driver.quit();
    }
    @Test(groups={"Negative","Regression","Smoke"})
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
