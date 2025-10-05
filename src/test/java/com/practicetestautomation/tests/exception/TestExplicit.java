package com.practicetestautomation.tests.exception;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestExplicit {
    private WebDriver driver; //Declaring it in class so that driver can be used within all methods.
    @BeforeMethod(alwaysRun = true)
    @Parameters("Browser")
    public void setup(String Browser){
        System.out.println("Running Test in:" + Browser);
        driver=new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        driver.manage().window().maximize();
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void TestExplicitWait(){
        //Explicit Wait
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        //Click Add button
        WebElement addClick= driver.findElement(By.id("add_btn"));
        addClick.click();
        WebElement row2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        // Verify Row 2 input field is displayed
        //WebElement row2=driver.findElement(By.xpath("//div[@id='row2']/input"));
        Assert.assertTrue(row2.isDisplayed(),"Row 2 was added message is not displayed");
    }
    @Test
    public void TestTimeOutException(){
        //Click Add button
        WebElement addClick= driver.findElement(By.id("add_btn"));
        addClick.click();
        //Wait for 3 seconds for the second input field to be displayed
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement threeSec=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        //Verify second input field is displayed
        Assert.assertTrue(threeSec.isDisplayed());
    }

    public void TestElementNotInteractableException(){
        //Click Add button
        WebElement addClick= driver.findElement(By.id("add_btn"));
        addClick.click();
        //Wait for the second row to load
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        //Type text into the second input field
        WebElement threeSec=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        threeSec.click();
        threeSec.sendKeys("Test");
        //Push Save button using locator By.name(“Save”)
        
        //Verify text saved
    }
}
