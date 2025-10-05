package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessfulLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By logoutButtonLocator=By.linkText("Log out");

    public SuccessfulLoginPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    // Verify new page URL contains practicetestautomation.com/logged-in-successfully/
    public String currentUrlTest(){
        return driver.getCurrentUrl();
    }
    public String pageSourceUrl(){
        return driver.getPageSource();
    }
    public void checkUrl(){

    }
    // Verify new page contains expected text ('Congratulations' or 'successfully logged in')

    // Verify button Log out is displayed on the new page
    public boolean logoutButtonVisible() {
        try {
            return driver.findElement(logoutButtonLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
